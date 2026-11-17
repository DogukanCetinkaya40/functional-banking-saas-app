package com.example.demo.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String tcNum;
    private String name;
    private String surname;
    private List<AccountResponse> accounts;
}
