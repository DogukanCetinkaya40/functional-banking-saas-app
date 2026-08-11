package com.example.demo.service;

import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repository.AccountRepository;
import com.example.demo.entity.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account hesapOlustur(Account account) {

        return accountRepository.save(account);
    }

    public List<Account> tumHesaplariGetir() {

        return accountRepository.findAll();
    }

    public Account hesapBul (UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şu ID sorgusuna ait hesap bulunamadı: " + id));
    }

    @Transactional
    public void paraTransferi(UUID gonderenID, UUID alanID, BigDecimal miktar) {

        Account gonderen = accountRepository.findById(gonderenID)
                .orElseThrow(() -> new RuntimeException("Gönderen hesap ID sorgusuna ait hesap bulunamadı:"));

        Account alan = accountRepository.findById(alanID)
                .orElseThrow(() -> new RuntimeException("Alan hesap ID sorgusuna ait hesap bulunamadı:"));

        if (gonderen.getBakiye().compareTo(miktar) < 0) {throw new IllegalArgumentException("İşlem tamamlanamadı, sebebi: Yetersiz gönderen bakiyesi.");}

        gonderen.setBakiye(gonderen.getBakiye().subtract(miktar));
        alan.setBakiye(alan.getBakiye().add(miktar));

        accountRepository.save(gonderen);
        accountRepository.save(alan);


    }

}
