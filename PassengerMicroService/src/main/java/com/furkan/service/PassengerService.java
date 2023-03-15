package com.furkan.service;

import com.furkan.mapper.IPassengerMapper;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.IPassengerAuthoRepository;
import com.furkan.repository.IPassengerRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Passenger;
import com.furkan.repository.entity.PassengerAutho;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService extends ServiceManagerImpl<Passenger,Long> {

    private final IPassengerRepository passengerRepository;
    private final IPassengerAuthoRepository passengerAuthoRepository;

    public PassengerService(IPassengerRepository passengerRepository, IPassengerAuthoRepository passengerAuthoRepository) {
        super(passengerRepository);
        this.passengerRepository = passengerRepository;
        this.passengerAuthoRepository = passengerAuthoRepository;
    }

    public void save(ModelSave modelSavePassenger){
        Passenger passenger= IPassengerMapper.INSTANCE.toDriver(modelSavePassenger);
        passengerAuthoRepository.save(PassengerAutho.builder()
                        .passengerId(passenger.getId())
                        .autho(Autho.SLEEPER)
                .build());
        save(passenger);
    }
    public Optional<Passenger> findByAuthId(Long authId){
        Optional<Passenger> passenger= passengerRepository.findOptionalByAuthId(authId);
        if (passenger.get()==null) return Optional.empty();
        return passenger;
    }

    public void getMessage(ModelFanout fanout){
        System.out.println("passenger servise ici ,fanout dan şu mesaj geldi...: "+fanout.toString());
    }
}
