package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {

    @NotBlank(message = "Alıcı ibanı boş bırakılamaz!")
    private String alanIban;

    @NotNull(message = "Gönderen kişi ID bulunamadı!")
    private UUID gonderenID;

    @NotNull(message = "Lütfen geçerli bir miktar giriniz.")
    @Positive(message = "Transfer edilen para miktarı negatif olamaz!")
    private BigDecimal miktar;

}
