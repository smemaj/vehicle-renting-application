package com.spring.vehiclerenting.service.impl;

import com.spring.vehiclerenting.errors.exception.CannotUpdateStatusException;
import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.ApplicationStatus;
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

import static com.spring.vehiclerenting.model.ApplicationStatus.CREATED;
import static com.spring.vehiclerenting.model.ApplicationStatus.SUBMITTED;


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

    Date localDate = new Date();

    @Override
    public Set<Application> listApplications(){
        String headerAuth = request.getHeader("Authorization");

        String jwt= headerAuth.substring(7, headerAuth.length());

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);

        Set<Application> apps= new HashSet<>();
        for (Application app:user.getApplications()) {
            apps.add(app);
        }

        return apps;
    }

    @Override
    public Application createApplication(Long vehicleId, Date startDate, Date endDate) {
        Vehicle vehicle = this.vehicleRepository.getReferenceById(vehicleId);

        String headerAuth = request.getHeader("Authorization");

        String jwt= headerAuth.substring(7, headerAuth.length());

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);

        Application application = new Application(vehicle, user, startDate, endDate, CREATED);
        this.applicationRepository.save(application);

        vehicle.getApplications().add(application);
        this.vehicleRepository.save(vehicle);

        user.getApplications().add(application);
        this.userRepository.save(user);

        return application;
    }

    @Override
    public void deleteApplication(Long applicationId) {

        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Application app= this.applicationRepository.getReferenceById(applicationId);
        Vehicle vehicle=app.getVehicle();

        vehicle.getApplications().remove(app);
        this.vehicleRepository.save(vehicle);

        user.getApplications().remove(app);
        this.userRepository.save(user);

        this.applicationRepository.delete(app);
    }

    @Override
    public Application updateApplicationDates(Long applicationId, Date startDate, Date endDate){
        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);
        Application app= this.applicationRepository.getReferenceById(applicationId);
        Vehicle vehicle=app.getVehicle();

        app.setStartDate(startDate);
        app.setEndDate(endDate);

        vehicle.getApplications().add(app);
        user.getApplications().add(app);

        this.vehicleRepository.save(vehicle);
        this.userRepository.save(user);

        return this.applicationRepository.save(app);
    }

    @Override
    public Set<Application> filterByEndDate(){
        String headerAuth = request.getHeader("Authorization");
        String jwt= headerAuth.substring(7, headerAuth.length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        User user = this.userRepository.findByUsername(username);

        Set<Application> apps= new HashSet<>();
        for (Application app:user.getApplications()){
            if (app.getEndDate().before(localDate)){
                apps.add(app);
            }
        }
        return apps;
    }

    @Override
    public Application updateApplicationStatusUser(Long applicationId, ApplicationStatus status) throws CannotUpdateStatusException {
        Application app= this.applicationRepository.getReferenceById(applicationId);
        if (app.getStatus()==CREATED){
            app.setStatus(status);
        }else {
            throw new CannotUpdateStatusException(applicationId);
        }
        return this.applicationRepository.save(app);
    }

    @Override
    public Application updateApplicationStatusAdm(Long applicationId, ApplicationStatus status) throws CannotUpdateStatusException {
        Application app= this.applicationRepository.getReferenceById(applicationId);
        if (app.getStatus()==SUBMITTED){
            app.setStatus(status);
        }else {
            throw new CannotUpdateStatusException(applicationId);
        }
        return this.applicationRepository.save(app);
    }




}
