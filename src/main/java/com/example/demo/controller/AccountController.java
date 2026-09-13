package com.example.demo.controller;

import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountSaveRequest;
import com.example.demo.dto.TransferRequest;
import com.example.demo.dto.TransferResponse;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse hesapOlustur(@Valid @RequestBody AccountSaveRequest accountSaveRequest) {
        return accountService.hesapOlustur(accountSaveRequest);
    }

    @GetMapping
    public List<AccountResponse> tumHesaplariGetir() {
        return accountService.tumHesaplariGetir();
    }

    // 3. ID'ye Göre Hesap Getir (GET http://localhost:8080/accounts/1)
    @GetMapping("/{id}")
    public AccountResponse hesapBul(@PathVariable UUID id) {
        return accountService.hesapBul(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> paraTransferi(@Valid @RequestBody TransferRequest request) {

        TransferResponse response = accountService.paraTransferi(request);

        return ResponseEntity.ok(response);
    }
}
