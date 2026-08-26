package com.example.demo.mapper;


import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.UserResponse;
import com.example.demo.dto.UserSaveRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserSaveRequest userSaveRequest);

    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> userList);

    AccountResponse toAccountResponse(Account account);

}
