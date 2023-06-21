package com.furkan.service;

import com.furkan.rabbitmq.messagemodel.ModelSave;
import com.furkan.repository.IDriverRepository;
import com.furkan.repository.entity.Driver;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;


@Service
public class DriverService extends ServiceManagerImpl<Driver, Long> {

    private final JwtTokenManager jwtTokenManager;
    private final DriverAuthoService driverAuthoService;
    private final IDriverRepository driverRepository;

    public DriverService(IDriverRepository driverRepository, DriverAuthoService driverAuthoService, JwtTokenManager jwtTokenManager) {
        super(driverRepository);
        this.driverRepository = driverRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.driverAuthoService = driverAuthoService;
    }

    public void save(ModelSave modelSaveDriver) {
        driver.setExperience(0);
        save(driver);
        driverAuthoService.setDriverAutho(driver.getAuthId());
    }



}

