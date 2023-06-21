package com.furkan.service;

import com.furkan.repository.IDriverAuthoRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.DriverAutho;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.cglib.proxy.LazyLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverAuthoService extends ServiceManagerImpl<DriverAutho,Long> {

    private final IDriverAuthoRepository driverAuthoRepository;

    public DriverAuthoService( IDriverAuthoRepository driverAuthoRepository) {
        super(driverAuthoRepository);
        this.driverAuthoRepository = driverAuthoRepository;
    }


    public List<Autho> getDriverAutho(Long driverId){
        List<DriverAutho> driverAuthorizations=driverAuthoRepository.findOptionalByDriverId(driverId).get();
        List<Autho> enumList=new ArrayList<>();
        for (DriverAutho driver: driverAuthorizations){
            Autho anEnum=driver.getAutho();
            enumList.add(anEnum);
        }
        return enumList;
    }

    public void setDriverAutho(Long driverId){
        List<DriverAutho> authoList= driverAuthoRepository.findOptionalByDriverId(driverId).get().stream().toList();
        DriverAutho driverAutho=null;
        if (authoList.isEmpty()||authoList.stream().count()==0){
             driverAutho = DriverAutho.builder()
                    .autho(Autho.BASIC)
                    .driverId(driverId)
                    .build();
        }
        driverAuthoRepository.save(driverAutho);
    }
    public void setDriverAutho(Long driverId,Autho autho){
        driverAuthoRepository.save(DriverAutho.builder()
                        .autho(autho)
                        .driverId(driverId)
                .build());
    }
}
