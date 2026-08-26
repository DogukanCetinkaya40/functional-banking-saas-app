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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String kart_No;
    private String son_Tarih;
    private String cvv;
    private BigDecimal kart_Limit;
    private BigDecimal guncel_Borc;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
