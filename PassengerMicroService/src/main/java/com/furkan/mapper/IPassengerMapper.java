package com.furkan.mapper;

import com.furkan.dto.StartRideDto;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.rabbitmq.messagemodel.ModelStartRide;
import com.furkan.repository.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPassengerMapper {
    IPassengerMapper INSTANCE= Mappers.getMapper(IPassengerMapper.class);
    Passenger toPassenger(final ModelSave model);
    ModelStartRide toModelStartRide(final StartRideDto dto);

}
