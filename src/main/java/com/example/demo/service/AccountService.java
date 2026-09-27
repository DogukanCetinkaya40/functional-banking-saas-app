package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.repository.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repository.AccountRepository;
import com.example.demo.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionHistoryRepository transactionHistoryRepository;
    private final TransactionMapper transactionMapper;

    public AccountService(AccountMapper accountMapper, AccountRepository accountRepository, TransactionHistoryRepository transactionHistoryRepository, TransactionMapper transactionMapper) {

        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.transactionMapper = transactionMapper;
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
    public TransferResponse paraTransferi(TransferRequest request) {

        Account gonderen = accountRepository.findById(request.getGonderenID())
                .orElseThrow(() -> new BusinessException("Gönderen hesap ID sorgusuna ait hesap bulunamadı:"));

        Account alan = accountRepository.findByIban(request.getAlanIban())
                .orElseThrow(() -> new BusinessException("Geçersiz alıcı IBAN :"));

        if (gonderen.getIban().equals(alan.getIban())) {
            throw new BusinessException("Kendi hesabınıza para gönderemezsiniz!");
        }

        if (gonderen.getBakiye().compareTo(request.getMiktar()) < 0) {
            throw new BusinessException("İşlem tamamlanamadı, sebebi: Yetersiz gönderen bakiyesi.");
        }

        if (!gonderen.getDoviz().equals(alan.getDoviz())) {
            throw new BusinessException("Alıcı ve gönderen döviz türü uyuşmuyor!");
        }
        gonderen.setBakiye(gonderen.getBakiye().subtract(request.getMiktar()));
        alan.setBakiye(alan.getBakiye().add(request.getMiktar()));

        accountRepository.save(gonderen);
        accountRepository.save(alan);

        TransactionHistory history = TransactionHistory.builder()
                .islemReferansNo(UUID.randomUUID())
                .gonderenHesap(gonderen)
                .aliciHesap(alan)
                .miktar(request.getMiktar())
                .islemTipi("HAVALE/EFT")
                .build();

        transactionHistoryRepository.save(history);

        log.info("Transfer İşlemi Başarılı ve Kaydedildi | Referans No: {}", history.getIslemReferansNo());

        return TransferResponse.builder()
                .islemNumarasi(history.getIslemReferansNo())
                .mesaj("Transfer işlemi başarıyla gerçekleşti.")
                .gonderilenMiktar(history.getMiktar())
                .kalanMiktar(gonderen.getBakiye())
                .doviz(gonderen.getDoviz())
                .islemTarihi(history.getIslemTarihi())
                .build();

    }

    public Page<TransactionDto> hesapGecmisi(UUID accountId, int page, int size) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new BusinessException("Hesap bulunamadı!"));

        Pageable pageable = PageRequest.of(page, size, Sort.by("islemTarihi").descending());

        Page<TransactionHistory> islemGecmisiSayfasi = transactionHistoryRepository.findByGonderenHesapIdOrAliciHesapId(accountId, accountId, pageable);

        return islemGecmisiSayfasi.map(islem -> transactionMapper.toDto(islem, accountId));
    }

}
