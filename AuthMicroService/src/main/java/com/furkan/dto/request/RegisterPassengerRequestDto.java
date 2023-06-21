package com.furkan.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterPassengerRequestDto {
    private String name;
    private String surname;
    private String email;
    private int age;
    private String phone;
    private MultipartFile avatar;
    private String password;
    private String rePassword;

}
