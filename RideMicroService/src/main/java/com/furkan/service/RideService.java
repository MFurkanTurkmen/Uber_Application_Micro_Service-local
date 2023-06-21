package com.furkan.service;


import com.furkan.config.security.JwtUserDetail;
import com.furkan.mapper.IRideMapper;
import com.furkan.rabbitmq.messagemodel.ModelStartRide;
import com.furkan.repository.IRideRepository;
import com.furkan.repository.entity.Ride;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;



@Service
public class RideService extends ServiceManagerImpl<Ride,Long> {
    private final IRideRepository rideRepository;
    private final JwtTokenManager jwtTokenManager;



    public RideService(IRideRepository rideRepository, JwtTokenManager jwtTokenManager) {
        super(rideRepository);
        this.rideRepository = rideRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void startRide(ModelStartRide modelStartRide){
        Ride ride= IRideMapper.INSTANCE.toRide(modelStartRide);
        ride.setPassengerId(jwtTokenManager.validToken(modelStartRide.getToken()).get());
        /**
         * burada driver ıd olmadan yolculuk başlatılamamayı yapmamız lazım.
         */
        save(ride);
    }



}
