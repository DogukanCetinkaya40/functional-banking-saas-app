package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

    private Long id;
    private String tcNum;
    private String name;
    private String surname;
    private List<AccountResponse> accounts;
}
