package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{id}")
    public AccountResponse hesapBul(@PathVariable UUID id) {
        return accountService.hesapBul(id);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> paraTransferi(@Valid @RequestBody TransferRequest request) {

        TransferResponse response = accountService.paraTransferi(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}{ekstre}")
    public ResponseEntity<Page<TransactionDto>> hesapEkstresi(@PathVariable UUID accountId, @RequestParam int page, @RequestParam int size) {

        Page<TransactionDto> ekstre = accountService.hesapGecmisi(accountId, page, size);

        return ResponseEntity.ok(ekstre);

    }
}
