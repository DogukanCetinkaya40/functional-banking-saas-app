package controller;

import entity.Account;
import service.AccountService;
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
    public Account hesapOlustur(@RequestBody Account account) {
        return accountService.hesapOlustur(account);
    }

    @GetMapping
    public List<Account> tumHesaplariGetir() {
        return accountService.tumHesaplariGetir();
    }

    // 3. ID'ye Göre Hesap Getir (GET http://localhost:8080/accounts/1)
    @GetMapping("/{id}")
    public Account hesapBul(@PathVariable UUID id) {
        return accountService.hesapBul(id);
    }
}
