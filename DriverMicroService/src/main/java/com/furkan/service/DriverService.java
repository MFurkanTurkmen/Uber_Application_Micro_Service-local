package com.furkan.service;

import com.furkan.dto.request.TokenDto;
import com.furkan.exception.DriverException;
import com.furkan.exception.EErrorType;
import com.furkan.mapper.IDriverMapper;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.IDriverAuthorizationsRepository;
import com.furkan.repository.IDriverRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Driver;
import com.furkan.repository.entity.DriverAuthorizations;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService extends ServiceManagerImpl<Driver,Long> {

    private final IDriverRepository driverRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IDriverAuthorizationsRepository driverAuthorizationsRepository;
    public DriverService(IDriverRepository driverRepository, JwtTokenManager jwtTokenManager, IDriverAuthorizationsRepository driverAuthorizationsRepository) {
        super(driverRepository);
        this.driverRepository = driverRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.driverAuthorizationsRepository = driverAuthorizationsRepository;
    }

    public List<Driver> getDriver(){
            return findAll();
    }

    public Optional<Driver> findByAuthId(Long id){
        return driverRepository.findOptionalByAuthId(id);
    }



    public void save(ModelSave modelSaveDriver){
        Driver driver=IDriverMapper.INSTANCE.toDriver(modelSaveDriver);
        save(driver);
        setDriverAuth(driver.getId());
    }
    public void setDriverAuth(Long driverId){
        System.out.println("driver service ici gelen id..."+ driverId);
        DriverAuthorizations driverAuthorizations= DriverAuthorizations.builder()
                .autho(Autho.BASIC)
                .driverId(driverId)
                .build();
        driverAuthorizationsRepository.save(driverAuthorizations);
    }

    public void getMessage(ModelFanout fanout){
        System.out.println("driver  driver driver servise faounttan mesaj alma işlemi mesajını.....:"+fanout.toString());
    }

    public List<Driver> findAll(String token){
        Optional<Long> auhdId=jwtTokenManager.validToken(token);
        Optional<Driver> driver = driverRepository.findOptionalByAuthId(auhdId.get());
        if (driver.isEmpty()) throw new DriverException(EErrorType.KULLANICI_BULUNAMADI);
        return findAll();
    }
}
