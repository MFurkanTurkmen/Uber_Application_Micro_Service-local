package com.furkan.service;


import com.furkan.mapper.IRideMapper;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.repository.IRideRepository;
import com.furkan.repository.entity.Ride;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



@Service
public class RideService extends ServiceManagerImpl<Ride,Long> {
    private final IRideRepository rideRepository;
    private final IRideMapper rideMapper;

    public RideService(JpaRepository<Ride, Long> repository, IRideRepository rideRepository, IRideMapper rideMapper) {
        super(repository);
        this.rideRepository = rideRepository;
        this.rideMapper = rideMapper;
    }










    public void getMessage(ModelFanout fanout){
        System.out.println("passenger servise ici ,fanout dan şu mesaj geldi...: "+fanout.toString());
    }
}
