package com.example.demo.controller;

import com.example.demo.dto.CreditCardResponse;
import com.example.demo.dto.CreditCardSaveRequest;
import jakarta.validation.Valid;
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
    public CreditCardResponse kartOlustur(@Valid @RequestBody CreditCardSaveRequest creditCardSaveRequest) {
        return creditCardService.kartOlustur(creditCardSaveRequest);
    }

    @GetMapping
    public List<CreditCardResponse> tumKartlar() {
        return creditCardService.tumKartlar();
    }

    @GetMapping("/{id}")
    public CreditCardResponse kartBul(@PathVariable UUID id) {
        return creditCardService.kartBul(id);
    }

}
