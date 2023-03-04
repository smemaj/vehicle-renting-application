package com.spring.vehiclerenting.service.impl;

import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.User;
import com.spring.vehiclerenting.model.Vehicle;
import com.spring.vehiclerenting.repository.ApplicationRepository;
import com.spring.vehiclerenting.repository.UserRepository;
import com.spring.vehiclerenting.repository.VehicleRepository;
import com.spring.vehiclerenting.service.ApplicationService;
import com.spring.vehiclerenting.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Set<Application> listApplications(){
        String headerAuth = request.getHeader("Authorization");

        String jwt= headerAuth.substring(7, headerAuth.length());

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);

        return user.getApplications();
    }

    @Override
    public Application createApplication(Long vehicleId, Date startDate, Date endDate) {
        Vehicle vehicle = this.vehicleRepository.getReferenceById(vehicleId);

        String headerAuth = request.getHeader("Authorization");

        String jwt= headerAuth.substring(7, headerAuth.length());

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);

        Application application = new Application("submitted", vehicle, user, startDate, endDate);
        this.applicationRepository.save(application);

        vehicle.getApplications().add(application);
        this.vehicleRepository.save(vehicle);

        user.getApplications().add(application);
        this.userRepository.save(user);

        return application;
    }

    @Override
    public void deleteApplication(Long applicationId) {
        //Vehicle vehicle = this.vehicleRepository.getReferenceById(vehicleId);

        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Application app= this.applicationRepository.getReferenceById(applicationId);
        Vehicle vehicle=app.getVehicle();

        vehicle.getApplications().remove(app);
        user.getApplications().remove(app);
        this.applicationRepository.deleteById(applicationId);
    }


}
