package com.example.demo.dto;

import com.example.demo.validation.ValidTCNo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@NotBlank
public class AuthenticationRequest {

    @NotBlank
    @ValidTCNo
    private String tcNum;

    @NotBlank
    private String sifre;

}
