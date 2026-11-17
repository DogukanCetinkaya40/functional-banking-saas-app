package com.example.demo.service;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.RefreshTokenRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByTcNum(request.getTcNum()).isPresent()) {
            throw new BusinessException("Bu TC Kimlik ile oluşturulmuş bir hesap zaten var!");
        }

        User user = User.builder()
                .tcNum(request.getTcNum())
                .surname(request.getAdSoyad())
                .sifre(request.getSifre())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        RefreshToken refreshToken =refreshTokenService.createRefreshToken(user.getId());

        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshToken.getToken())
                .build();

    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        Optional<User> userOptional = userRepository.findByTcNum(request.getTcNum());

        if (userOptional.isEmpty()) {
            throw new BusinessException("TC Kimlik Numarası veya Şifre Hatalı.");
        }

        User user = userOptional.get();

        if (user.isAccountLocked()) {

            if (user.getSuspensionLevel() >= 4) {
                throw new BusinessException("Çok sayıda hatalı giriş denemesi sebebiyle hesabınız blokelenmiştir. Açılması için lütfen en yakın şube ile görüşünüz.");
            }

            if (user.getLockoutTime() != null) {
                long lockoutTime = switch (user.getSuspensionLevel()) {
                    case 1 -> 15;
                    case 2 -> 60;
                    case 3 -> 24 * 60;
                    default -> 0;
                };

                if (user.getLockoutTime().plusMinutes(lockoutTime).isBefore(LocalDateTime.now())) {
                    user.setAccountLocked(false);
                    user.setFailedLoginAttempts(0);
                    user.setLockoutTime(null);
                    userRepository.save(user);
                } else {
                    throw new BusinessException("Hesabınız geçici olarak blokelenmiştir. Lütfen daha sonra tekrar deneyiniz.");
                }
            }
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getTcNum(),
                            request.getSifre()

                    )
            );
        } catch (org.springframework.security.authentication.BadCredentialsException exception) {
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);

            if (user.getFailedLoginAttempts() >= 6) {
                user.setSuspensionLevel(user.getSuspensionLevel() + 1);
                user.setAccountLocked(true);
                user.setLockoutTime(LocalDateTime.now());

                String massage = switch (user.getSuspensionLevel()) {
                    case 1 -> "15 dakikalığına";
                    case 2 -> "1 saatliğine";
                    case 3 -> "1 gün için";
                    default -> "kalıcı olarak";
                };

                userRepository.save(user);
                throw new RuntimeException("Şifrenizi bir çok kez yanlış girdiniz. Hesabınız " + massage + "kilitlenmiştir.");

            }

            if (user.getFailedLoginAttempts() <= 3) {
                throw new BusinessException("TC Kimlik Numarası veya Şifre Hatalı.");
            } else {
                int remainingAttempts = 6 - user.getFailedLoginAttempts();
                throw new BusinessException("TC Kimlik Numarası veya Şifre Hatalı. DİKKAT Kalan giriş hakkınız: " + remainingAttempts);
            }
        }

        user.setFailedLoginAttempts(0);
        user.setSuspensionLevel(0);
        user.setLockoutTime(null);
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return AuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken());

        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken.getToken())
                .build();

    }

}
