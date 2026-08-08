package service;

import repository.AccountRepository;
import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void paraTransferi() {

    }

}
