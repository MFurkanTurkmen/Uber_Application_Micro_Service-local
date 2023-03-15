package com.furkan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRideMapper {
    IRideMapper INSTANCE= Mappers.getMapper(IRideMapper.class);
}
