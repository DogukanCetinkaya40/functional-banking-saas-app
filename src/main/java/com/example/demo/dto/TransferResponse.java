package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TransferResponse {

    private UUID islemNumarasi;
    private String mesaj;
    private BigDecimal gonderilenMiktar;
    private BigDecimal kalanMiktar;
    private String doviz;
    private LocalDateTime islemTarihi;

}
