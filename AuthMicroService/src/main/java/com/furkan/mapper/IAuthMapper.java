package com.furkan.mapper;

import com.furkan.dto.request.AuthLoginDto;
import com.furkan.dto.request.AuthSaveDto;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    Auth toAuth(final AuthSaveDto dto);
    AuthSaveDto toDto(final Auth auth);
    Auth toAuth(final AuthLoginDto dto);
    @Mapping(source = "id",target = "authId")
    ModelSave ToModel(final Auth auth);
}
