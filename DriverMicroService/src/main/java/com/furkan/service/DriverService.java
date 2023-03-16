package com.furkan.service;

import com.furkan.dto.request.BaseDriverDto;
import com.furkan.exception.DriverException;
import com.furkan.exception.EErrorType;
import com.furkan.mapper.IDriverMapper;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.IDriverRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.Driver;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DriverService extends ServiceManagerImpl<Driver,Long> {

    private final JwtTokenManager jwtTokenManager;
    private final DriverAuthoService driverAuthoService;
    private final IDriverRepository driverRepository;
    public DriverService(IDriverRepository driverRepository, DriverAuthoService driverAuthoService , JwtTokenManager jwtTokenManager) {
        super(driverRepository);
        this.driverRepository = driverRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.driverAuthoService = driverAuthoService;
    }

    public List<Driver> getDriver(){
            return findAll();
    }

    public Optional<Driver> findByAuthId(Long id){
        return driverRepository.findOptionalByAuthId(id);
    }


    public void save(ModelSave modelSaveDriver){
        System.out.println(modelSaveDriver.getAuthId());
        Driver driver=IDriverMapper.INSTANCE.toDriver(modelSaveDriver);
        System.out.println(driver.getAuthId());
        driver.setExperience(0);
        save(driver);
       driverAuthoService.setDriverAutho(driver.getAuthId());
    }

    public String authoDeneme(){
        return "deneme basarili";
    }

    public void getMessage(ModelFanout fanout){
        System.out.println("driver  driver driver servise faounttan mesaj alma işlemi mesajını.....:"+fanout.toString());
    }


    public String updateDriverExp(BaseDriverDto dto){
        Driver driver= findById(dto.getId()).get();

        if (driver==null) throw new DriverException(EErrorType.KULLANICI_BULUNAMADI);
        driver.setExperience(driver.getExperience()+ dto.getExp());
        if (driver.getExperience()>=5 && driver.getExperience() <10){
            driverAuthoService.setDriverAutho(driver.getId(), Autho.MIDDLE);
        }
        save(driver);
        return String.valueOf(driver.getUsername()+" kullanıcınıcısının yeni deneyim puanı: "+driver.getExperience()
        +" ve kullanıcının yetkileri: "+driverAuthoService.getDriverAutho(driver.getId()).toString());

    }
}
