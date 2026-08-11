package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransferRequest {

    private UUID alanID;
    private UUID gonderenID;
    private BigDecimal miktar;

}
