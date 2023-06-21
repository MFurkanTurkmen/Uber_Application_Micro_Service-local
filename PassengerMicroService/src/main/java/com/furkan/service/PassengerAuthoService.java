package com.furkan.service;

import com.furkan.repository.IPassengerAuthoRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Passenger;
import com.furkan.repository.entity.PassengerAutho;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerAuthoService extends ServiceManagerImpl<PassengerAutho,Long> {
    private final IPassengerAuthoRepository passengerAuthoRepository;

    public PassengerAuthoService(IPassengerAuthoRepository passengerAuthoRepository) {
        super(passengerAuthoRepository);
        this.passengerAuthoRepository = passengerAuthoRepository;
    }
    public List<Autho> getPassengerAutho(Long passengerId){
        List<PassengerAutho> passengerAuthoList=passengerAuthoRepository.findOptionalByPassengerId(passengerId).get();
        List<Autho> enumList=new ArrayList<>();
        for (PassengerAutho passenger: passengerAuthoList){
            Autho anEnum=passenger.getAutho();
            enumList.add(anEnum);
        }
        return enumList;
    }


    public void setDriverAutho(Long passengerId){
        List<PassengerAutho> authoList= passengerAuthoRepository.findOptionalByPassengerId(passengerId).get().stream().toList();
        PassengerAutho passengerAutho=null;
        if (authoList.isEmpty()||authoList.stream().count()==0){
            passengerAutho = passengerAutho.builder()
                    .autho(Autho.SLEEPER)
                    .passengerId(passengerId)
                    .build();
        }
        passengerAuthoRepository.save(passengerAutho);
    }
}
