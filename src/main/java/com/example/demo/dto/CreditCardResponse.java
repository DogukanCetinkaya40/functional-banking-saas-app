package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditCardResponse {

    private String kart_No;
    private String son_Tarih;
    private BigDecimal kart_Limit;
    private BigDecimal guncel_Borc;

}
