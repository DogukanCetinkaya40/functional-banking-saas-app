package service;

import entity.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User kullaniciOlustur(User user) {

        return userRepository.save(user);

    }

    public List<User> tumKullanicilar() {

        return userRepository.findAll();

    }

    public User kullaniciBul(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Girdiğiniz ID'ye uygun kullanıcı bulunamadı."));

    }

}
