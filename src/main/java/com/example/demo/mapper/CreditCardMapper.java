package com.example.demo.mapper;

import com.example.demo.dto.CreditCardResponse;
import com.example.demo.dto.CreditCardSaveRequest;
import com.example.demo.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    @Mapping(target = "kart_No", ignore = true)
    @Mapping(target = "son_Tarih", ignore = true)
    @Mapping(target = "cvv", ignore = true)
    @Mapping(target = "guncel_Borc", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    CreditCard toEntity(CreditCardSaveRequest creditCardSaveRequest);

    @Mapping(target = "kart_No", expression = "java(creditCardMasker(creditCard.getKart_No()))")
    CreditCardResponse toResponse(CreditCard creditCard);

    List<CreditCardResponse> toResponseList(List<CreditCard> creditCardList);

    default String creditCardMasker(String originalNumber) {
        if (originalNumber == null || originalNumber.length() < 4) {
            return "**** **** **** ****";
        }

        return "**** **** **** " + originalNumber.substring(originalNumber.length() - 4);
    }

}
