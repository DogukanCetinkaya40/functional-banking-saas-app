package com.example.demo.service;

import com.example.demo.dto.CreditCardResponse;
import com.example.demo.dto.CreditCardSaveRequest;
import com.example.demo.entity.Account;
import com.example.demo.entity.CreditCard;
import com.example.demo.mapper.CreditCardMapper;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import com.example.demo.repository.CreditCardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    private final AccountRepository accountRepository;

    public CreditCardService(CreditCardMapper creditCardMapper, CreditCardRepository creditCardRepository, AccountRepository accountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMapper = creditCardMapper;
        this.accountRepository = accountRepository;
    }

    public CreditCardResponse kartOlustur(CreditCardSaveRequest creditCardSaveRequest) {

        Account account = accountRepository.findById(creditCardSaveRequest.getAccountId())
                .orElseThrow(() -> new RuntimeException("Kartın bağlanacağı hesap bulunamadı."));

        CreditCard creditCard = creditCardMapper.toEntity(creditCardSaveRequest);

        creditCard.setAccount(account);
        creditCard.setKart_No(randomCardNumberGenerator());
        creditCard.setSon_Tarih(LocalDate.now().plusYears(5).toString());
        creditCard.setCvv(String.valueOf(new Random().nextInt(900) + 100));
        creditCard.setGuncel_Borc(BigDecimal.ZERO);

        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        return creditCardMapper.toResponse(savedCreditCard);

    }

    public List<CreditCardResponse> tumKartlar() {

        List<CreditCard> creditCardList = creditCardRepository.findAll();
        return creditCardMapper.toResponseList(creditCardList);

    }

    public CreditCardResponse kartBul(UUID id) {

        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Girdiğiniz ID sorgusuna ait kart bulunamadı."));

        return creditCardMapper.toResponse(creditCard);
    }

    private String randomCardNumberGenerator() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int n = 0; n < 16; n++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

}
