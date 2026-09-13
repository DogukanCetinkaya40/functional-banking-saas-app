package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "credit_cards")
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard extends BaseEntity {

    @Column(nullable = false)
    private String kart_No;

    @Column(nullable = false)
    private String son_Tarih;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private BigDecimal kart_Limit;

    @Column(nullable = false)
    private BigDecimal guncel_Borc;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
