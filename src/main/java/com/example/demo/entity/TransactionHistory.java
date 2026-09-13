package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistory extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private UUID islemReferansNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gondren_hesap_id", nullable = false, updatable = false)
    private Account gonderenHesap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alici_hesap_id", nullable = false, updatable = false)
    private Account aliciHesap;

    @Column(nullable = false, updatable = false)
    private BigDecimal miktar;

    @Column(nullable = false, updatable = false)
    private String islemTipi;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime islemTarihi;



}
