package com.example.demo.service;

import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserSaveRequest;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse kullaniciOlustur(UserSaveRequest request) {

        User user = new User();
        user.setTcNum(request.getTcNum());
        user.setName(request.getName());
        user.setSurname(request.getSurname());

        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);

    }

    public List<UserResponse> tumKullanicilar() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());

    }

    public User kullaniciBul(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Girdiğiniz ID'ye uygun kullanıcı bulunamadı."));

    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setTcNum(user.getTcNum());

        if (user.getAccounts() != null) {

            List<AccountResponse> accountResponses = user.getAccounts()
                    .stream()
                    .map(account -> {
                        AccountResponse accResponse = new AccountResponse();
                        accResponse.setIban(account.getIban());
                        accResponse.setId(account.getId());
                        accResponse.setDoviz(account.getDoviz());
                        accResponse.setBakiye(account.getBakiye());
                        return accResponse;
                    }).collect(Collectors.toList());

            response.setAccounts(accountResponses);
        }
        return response;
    }
}

