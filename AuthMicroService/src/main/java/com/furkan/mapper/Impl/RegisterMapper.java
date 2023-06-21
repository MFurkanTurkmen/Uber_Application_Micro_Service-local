package com.furkan.mapper.Impl;

import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.mapper.IRegisterMapper;
import com.furkan.rabbitmq.model.ModelRegisterDriver;
import com.furkan.rabbitmq.model.ModelRegisterPassenger;
import com.furkan.repository.entity.Auth;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper implements IRegisterMapper {

    @Override
    public ModelRegisterDriver toModel(RegisterDriverRequestDto dto, String avatar, String driverLicense) {
        ModelRegisterDriver model = ModelRegisterDriver.builder()
                .email(dto.getEmail())
                .age(dto.getAge())
                .surname(dto.getSurname())
                .name(dto.getName())
                .drivingLicence(driverLicense)
                .phone(dto.getPhone())
                .avatar(avatar)
                .car(dto.getCar())
                .build();
        return model;
    }

    @Override
    public ModelRegisterPassenger toModel(RegisterPassengerRequestDto dto, String avatar) {
        ModelRegisterPassenger model = ModelRegisterPassenger.builder()
                .age(dto.getAge())
                .avatar(avatar)
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .phone(dto.getPhone())
                .build();
        return model;
    }
}
