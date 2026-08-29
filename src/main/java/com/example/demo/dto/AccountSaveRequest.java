package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountSaveRequest {

    @NotNull(message = "Kullanıcı ID boş bırakılamaz.")
    @Positive(message = "Kullanıcı ID pozitif bir değer olmalı.")
    private UUID userId;

    @NotNull(message = "Döviz seçeneği boş bırakılamaz.")
    @Pattern(regexp = "^(TRY|USD|EUR|GBP|CNY|JPY)$", message = "Geçersiz döviz girdisi.")
    private String doviz;

}
