package com.furkan.dto.request;

import com.furkan.repository.entity.Type;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthSaveDto {
    private String username;
    private String name;
    private String surname;
    private String password;
    private String email;
    private Type type;
}
