package com.furkan.service;

import com.furkan.dto.DriverExpDto;
import com.furkan.dto.StartRideDto;
import com.furkan.mapper.IPassengerMapper;
import com.furkan.rabbitmq.messagemodel.ModelExpDriver;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.rabbitmq.messagemodel.ModelStartRide;
import com.furkan.rabbitmq.producer.ProducerDirectService;
import com.furkan.repository.IPassengerRepository;
import com.furkan.repository.entity.Passenger;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerService extends ServiceManagerImpl<Passenger,Long> {

    private final IPassengerRepository passengerRepository;
    private final ProducerDirectService producerDirectService;
    private final PassengerAuthoService passengerAuthoRepository;

    public PassengerService(IPassengerRepository passengerRepository , ProducerDirectService producerDirectService, PassengerAuthoService passengerAuthoRepository) {
        super(passengerRepository);
        this.passengerRepository = passengerRepository;
        this.producerDirectService = producerDirectService;
        this.passengerAuthoRepository = passengerAuthoRepository;
    }

    public void save(ModelSave modelSaveDriver) {
        Passenger passenger = IPassengerMapper.INSTANCE.toPassenger(modelSaveDriver);
        save(passenger);
        passengerAuthoRepository.setDriverAutho(passenger.getAuthId());
    }


    public String  setDriverExp(DriverExpDto dto){
        producerDirectService.sendExpDriver(ModelExpDriver.builder()
                        .driverId(dto.getDriverId())
                        .exp(dto.getExp())
                .build());
        return "basarili sekilde driver exp güncellendi";
    }

    public String startRide(StartRideDto dto){
        ModelStartRide modelStartRide= IPassengerMapper.INSTANCE.toModelStartRide(dto);
        producerDirectService.sendStartRide(modelStartRide);
        return "yolculuk baslatildi";
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
