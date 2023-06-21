package com.furkan.mapper;

import com.furkan.rabbitmq.messagemodel.ModelStartRide;
import com.furkan.repository.entity.Ride;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRideMapper {
    IRideMapper INSTANCE= Mappers.getMapper(IRideMapper.class);
    Ride toRide(final ModelStartRide modelStartRide);
}
