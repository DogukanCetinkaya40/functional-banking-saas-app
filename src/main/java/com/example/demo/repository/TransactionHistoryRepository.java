package com.example.demo.repository;

import com.example.demo.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistory, UUID> {

    @EntityGraph(attributePaths = {"gonderenHesap", "aliciHesap"})
    Page<TransactionHistory> findByGonderenHesapIdOrAliciHesapId(UUID gonderenId, UUID aliciId, Pageable pageable);

}
