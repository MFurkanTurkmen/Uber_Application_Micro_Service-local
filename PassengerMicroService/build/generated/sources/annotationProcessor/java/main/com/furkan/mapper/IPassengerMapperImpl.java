package com.furkan.mapper;

import com.furkan.dto.StartRideDto;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.rabbitmq.messagemodel.ModelStartRide;
import com.furkan.repository.entity.Passenger;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-03T03:16:06+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class IPassengerMapperImpl implements IPassengerMapper {

    @Override
    public Passenger toPassenger(ModelSave model) {
        if ( model == null ) {
            return null;
        }

        Passenger.PassengerBuilder<?, ?> passenger = Passenger.builder();

        passenger.authId( model.getAuthId() );
        passenger.username( model.getUsername() );
        passenger.name( model.getName() );
        passenger.surname( model.getSurname() );
        passenger.email( model.getEmail() );

        return passenger.build();
    }

    @Override
    public ModelStartRide toModelStartRide(StartRideDto dto) {
        if ( dto == null ) {
            return null;
        }

        ModelStartRide.ModelStartRideBuilder modelStartRide = ModelStartRide.builder();

        modelStartRide.driverId( dto.getDriverId() );
        modelStartRide.price( dto.getPrice() );
        modelStartRide.token( dto.getToken() );

        return modelStartRide.build();
    }
}
