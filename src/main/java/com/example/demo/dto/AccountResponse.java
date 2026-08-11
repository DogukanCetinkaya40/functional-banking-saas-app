package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountResponse {

    private UUID id;
    private String iban;
    private BigDecimal bakiye;
    private String doviz;

}
