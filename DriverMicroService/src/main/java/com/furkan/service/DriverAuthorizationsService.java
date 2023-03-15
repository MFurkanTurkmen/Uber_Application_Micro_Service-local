package com.furkan.service;

import com.furkan.repository.IDriverAuthorizationsRepository;
import com.furkan.repository.entity.Autho;
import com.furkan.repository.entity.DriverAuthorizations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverAuthorizationsService {
    private final IDriverAuthorizationsRepository driverAuthorizationsRepository;
    public List<Autho> driverIdileYetkiListesi(Long driverId){
        List<DriverAuthorizations> driverAuthorizations=driverAuthorizationsRepository.findByDriverId(driverId);
        List<Autho> enumList=new ArrayList<>();
        for (DriverAuthorizations driver: driverAuthorizations){
            Autho anEnum=driver.getAutho();
            enumList.add(anEnum);
        }
        return enumList;
    }
}
