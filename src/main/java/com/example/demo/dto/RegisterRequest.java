package com.example.demo.dto;

import com.example.demo.validation.ValidTCNo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Kullanıcı adı veya şifre yanlış.")
    @ValidTCNo
    private String tcNum;

    @NotBlank(message = "Kullanıcı adı veya şifre yanlış.")
    private String adSoyad;

    @NotBlank(message = "Kullanıcı adı veya şifre yanlış.")
    private String sifre;

}
