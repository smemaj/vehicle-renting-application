package com.spring.vehiclerenting.security.service.impl;

import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.Role;
import com.spring.vehiclerenting.model.User;
import com.spring.vehiclerenting.model.Vehicle;
import com.spring.vehiclerenting.repository.ApplicationRepository;
import com.spring.vehiclerenting.repository.VehicleRepository;
import com.spring.vehiclerenting.security.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ApplicationRepository applicationRepository;
    @Override
    public Application createApplication(Long vehicleId) {
        Vehicle vehicle = this.vehicleRepository.getReferenceById(vehicleId);
        System.out.println(vehicle);
        Application application = new Application(vehicle);
        this.applicationRepository.save(application);
        return application;
    }

}
