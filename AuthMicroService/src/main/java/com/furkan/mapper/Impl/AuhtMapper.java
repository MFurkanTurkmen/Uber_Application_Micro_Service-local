package com.furkan.mapper.Impl;

import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.mapper.IAuthMapper;
import com.furkan.repository.entity.Auth;
import com.furkan.repository.entity.enums.Type;
import org.springframework.stereotype.Component;

@Component
public class AuhtMapper implements IAuthMapper {
    @Override
    public Auth toAuth(RegisterPassengerRequestDto dto) {

        Auth auth =Auth.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .type(Type.PASSENGER)
                .build();
        return auth;
    }

    @Override
    public Auth toAuth(RegisterDriverRequestDto dto) {
        Auth auth =Auth.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .type(Type.DRIVER)
                .build();
        return auth;
    }
}
