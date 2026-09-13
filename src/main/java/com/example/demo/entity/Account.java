package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import lombok.*;

@Entity
@Data
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    @Column(nullable = false)
    private String iban;

    @Column(nullable = false)
    private BigDecimal bakiye;

    @Column(nullable = false)
    private String doviz;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

