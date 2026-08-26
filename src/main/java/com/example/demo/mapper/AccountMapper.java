package com.example.demo.mapper;

import com.example.demo.dto.AccountResponse;
import com.example.demo.dto.AccountSaveRequest;
import com.example.demo.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "iban", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bakiye", ignore = true)

    Account toEntity(AccountSaveRequest accountSaveRequest);

    AccountResponse toResponse(Account account);

    List<AccountResponse> toResponseList(List<Account> accountList);

}
