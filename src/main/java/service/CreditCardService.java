package service;

import entity.CreditCard;
import org.springframework.stereotype.Service;
import repository.CreditCardRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditCardService {

    public final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard kartOlustur(CreditCard creditCard) {

        return creditCardRepository.save(creditCard);

    }

    public List<CreditCard> tumKartlar() {

        return creditCardRepository.findAll();

    }

    public CreditCard kartBul(UUID id) {

        return creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Girdiğiniz ID sorgusuna ait kart bulunamadı."));
    }

}
