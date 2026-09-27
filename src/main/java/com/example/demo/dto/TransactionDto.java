package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransactionDto {

    private UUID referansNo;
    private String islemYonu;
    private String karsiIban;
    private BigDecimal miktar;
    private String doviz;
    private LocalDateTime islemTarihi;

}
