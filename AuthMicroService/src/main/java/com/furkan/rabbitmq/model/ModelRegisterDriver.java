package com.furkan.rabbitmq.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelRegisterDriver implements Serializable {
    private String name;
    private String surname;
    private String email;
    private int age;
    private String phone;
    private String avatar;
    private String car;
    private String drivingLicence;

}
