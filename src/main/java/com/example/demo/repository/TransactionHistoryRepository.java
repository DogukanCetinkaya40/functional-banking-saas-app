package com.example.demo.repository;

import com.example.demo.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.UUID;

public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistory, UUID> {

    Page<TransactionHistory> findByGonderenHesapIdOrAliciHesapId(UUID gonderenId, Long aliciId, Pageable pageable);

}
