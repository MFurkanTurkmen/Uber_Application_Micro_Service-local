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
    public List<Autho> findAuthoByPassengerId(Long passengerID){
        List<Autho> authoList= new ArrayList<>();
        for (PassengerAutho autho: passengerAuthoRepository.findByPassengerId(passengerID)){
            authoList.add(autho.getAutho());
        }
        return authoList;
    }
}
