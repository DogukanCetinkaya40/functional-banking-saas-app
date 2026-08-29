package com.example.demo.dto;

import com.example.demo.validation.ValidTCNo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSaveRequest {

@ValidTCNo
private String tcNum;

@NotBlank(message = "isim ve soy isim boş bırakılamaz!")
@Size(min = 2, max = 50, message = "Ad 2 ila 50 karakter arası uzunlukta olmalı!")
private String name;

@NotBlank(message = "isim ve soy isim boş bırakılamaz!")
@Size(min = 2, max = 50, message = "Soyad 2 ila 50 karakter arası uzunlukta olmalı!")
private String surname;

@NotBlank(message = "Şifre alanı boş bırakılamaz!")
@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$_%^&*]).{8,}$", message = "Şifre en az 8 karakter uzunlukta olmalı ve şunları içermelidir: en az 1 büyük karakter, 1 küçük karakter, 1 sayı ve ve 1 özel karakter (!,@,#,$,_,%,^,&,*)")
private String sifre;

}
