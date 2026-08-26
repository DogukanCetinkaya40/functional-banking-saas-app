package com.example.demo.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountSaveRequest {

    private UUID userId;
    private String doviz;

}
