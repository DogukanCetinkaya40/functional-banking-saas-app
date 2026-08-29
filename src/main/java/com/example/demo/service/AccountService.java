package com.example.demo.service;

import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountSaveRequest;
import com.example.demo.mapper.AccountMapper;
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
    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper, AccountRepository accountRepository) {

        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    public AccountResponse hesapOlustur(AccountSaveRequest accountSaveRequest) {

        Account account = accountMapper.toEntity(accountSaveRequest);
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toResponse(savedAccount);
    }

    public List<AccountResponse> tumHesaplariGetir() {

        List<Account> accountList = accountRepository.findAll();
        return accountMapper.toResponseList(accountList);
    }

    public AccountResponse hesapBul (UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şu ID sorgusuna ait hesap bulunamadı: " + id));

        return accountMapper.toResponse(account);
    }

    @Transactional
    public void paraTransferi(UUID gonderenID, UUID alanID, BigDecimal miktar) {

        Account gonderen = accountRepository.findById(gonderenID)
                .orElseThrow(() -> new RuntimeException("Gönderen hesap ID sorgusuna ait hesap bulunamadı:"));

        Account alan = accountRepository.findById(alanID)
                .orElseThrow(() -> new RuntimeException("Alan hesap ID sorgusuna ait hesap bulunamadı:"));

        if (gonderen.getBakiye().compareTo(miktar) < 0) {throw new IllegalArgumentException("İşlem tamamlanamadı, sebebi: Yetersiz gönderen bakiyesi.");}

        if (gonderen.getIban().equals(alan.getIban())) {
            throw new RuntimeException("Kendi hesabınıza para gönderemezsiniz!");
        }
        gonderen.setBakiye(gonderen.getBakiye().subtract(miktar));
        alan.setBakiye(alan.getBakiye().add(miktar));

        accountRepository.save(gonderen);
        accountRepository.save(alan);


    }

}
