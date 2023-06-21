package com.furkan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {
    BAD_REQUEST_ERROR(1000,"Geçersiz Parametre Girişi Yaptınız",BAD_REQUEST),
    INTERNAL_ERROR(3000,"Sunucuda beklenmeyen hata",INTERNAL_SERVER_ERROR),


    //NOT NULL
    PASSWORD_NOT_NULL(1111,"SİFRE BOS OLAMAZ.",BAD_REQUEST),
    PASSWORD_NOT_MATCH(1112,"SİFRELER UYUSMUYOR.",BAD_REQUEST),
    EMAİL_NOT_NULL(1113,"EMAIL BOS OLAMAZ.",BAD_REQUEST),
    NAME_NOT_NULL(1114,"ISIM BOS GECILEMEZ",BAD_REQUEST),

    // IS ****
    EMAIL_ALREADY_REGİSTERED(1211,"EMAIL ALREADY REGISTERED PLEASE TRY ANOTHER EMAIL",BAD_REQUEST),

    INVALID_TOKEN(4001,"GECERSIZ TOKEN",BAD_REQUEST),
    KULLANICI_BULUNAMADI(2301,"Aradığınız id ye ait kullanıcı bulunamamıştır",INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
