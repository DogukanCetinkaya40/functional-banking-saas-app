package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreditCardSaveRequest {

    private UUID accountId;
    private BigDecimal kart_Limit;

}
