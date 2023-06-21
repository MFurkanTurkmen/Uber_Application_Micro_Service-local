package com.furkan.mapper;

import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.repository.entity.Auth;

public interface IAuthMapper {
    Auth toAuth(final RegisterPassengerRequestDto dto);
    Auth toAuth(final RegisterDriverRequestDto dto);

}
