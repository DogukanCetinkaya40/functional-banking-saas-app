package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreditCardSaveRequest {

    @NotNull(message = "Hesap ID boş bırakılamaz.")
    @Positive(message = "Hesap ID pozitif bir değer olmalı.")
    private UUID accountId;

    @NotNull(message = "Lütfen kart limiti seçiniz.")
    @Positive(message = "Kart limiti pozitif bir değer olmalı.")
    private BigDecimal kart_Limit;

}
