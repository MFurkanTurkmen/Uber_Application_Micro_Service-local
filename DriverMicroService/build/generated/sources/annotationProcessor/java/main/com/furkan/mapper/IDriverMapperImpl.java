package com.furkan.mapper;

import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.entity.Driver;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-14T04:27:13+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class IDriverMapperImpl implements IDriverMapper {

    @Override
    public Driver toDriver(ModelSave model) {
        if ( model == null ) {
            return null;
        }

        Driver.DriverBuilder<?, ?> driver = Driver.builder();

        driver.authId( model.getAuthId() );
        driver.username( model.getUsername() );
        driver.name( model.getName() );
        driver.surname( model.getSurname() );
        driver.email( model.getEmail() );

        return driver.build();
    }
}
