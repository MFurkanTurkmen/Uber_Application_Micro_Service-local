package com.furkan.mapper;

import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.rabbitmq.model.ModelRegisterDriver;
import com.furkan.rabbitmq.model.ModelRegisterPassenger;
import com.furkan.repository.entity.Auth;

public interface IRegisterMapper {

    ModelRegisterDriver toModel(final RegisterDriverRequestDto dto,String avatar, String driverLicense);
    ModelRegisterPassenger toModel(final RegisterPassengerRequestDto dto, String avatar);
}
