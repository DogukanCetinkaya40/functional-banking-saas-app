package com.example.demo.mapper;

import com.example.demo.dto.TransactionDto;
import com.example.demo.entity.TransactionHistory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionMapper {

    public TransactionDto toDto(TransactionHistory islem, UUID sorgulayanHesapId) {

        if (islem == null) {
            return null;
        }

        boolean paraCikisiMi = islem.getGonderenHesap().getId().equals(sorgulayanHesapId);

        return TransactionDto.builder()
                .referansNo(islem.getIslemReferansNo())
                .islemYonu(paraCikisiMi ? "GİDEN" : "GELEN")
                .karsiIban(paraCikisiMi ? islem.getAliciHesap().getIban() : islem.getGonderenHesap().getIban())
                .doviz(islem.getDoviz())
                .miktar(islem.getMiktar())
                .islemTarihi(islem.getIslemTarihi())
                .build();
    }

}
