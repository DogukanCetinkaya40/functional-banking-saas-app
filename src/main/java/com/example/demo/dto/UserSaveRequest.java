package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSaveRequest {

@NotBlank(message = "TC Kimlik Numarası Boş Bırakılamaz!")
@Pattern(regexp = "^[1-9]{1}[0-9]{10}$", message = "TC Kimlik numarası 11 haneli olmalı ve 0 ile başlamamalı!")
private String tcNum;

@NotBlank(message = "isim ve soy isim boş bırakılamaz!")
@Size(min = 2, max = 50, message = "Ad 2 ila 50 karakter arası uzunlukta olmalı!")
private String name;

@NotBlank(message = "isim ve soy isim boş bırakılamaz!")
@Size(min = 2, max = 50, message = "Soyad 2 ila 50 karakter arası uzunlukta olmalı!")
private String surname;

private String sifre;

}
