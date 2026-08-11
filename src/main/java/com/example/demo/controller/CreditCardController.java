package com.example.demo.controller;

import com.example.demo.entity.CreditCard;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.CreditCardService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/credit_cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public CreditCard kartOlustur(@RequestBody CreditCard creditCard) {
        return creditCardService.kartOlustur(creditCard);
    }

    @GetMapping
    public List<CreditCard> tumKartlar() {
        return creditCardService.tumKartlar();
    }

    @GetMapping("/{id}")
    public CreditCard kartBul(@PathVariable UUID id) {
        return creditCardService.kartBul(id);
    }

}
