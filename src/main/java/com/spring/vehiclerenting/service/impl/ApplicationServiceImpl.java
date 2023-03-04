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
import com.spring.vehiclerenting.config.filter.AuthTokenFilter;
import org.springframework.util.StringUtils;

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
    public Application createApplication(Long vehicleId) {
        Vehicle vehicle = this.vehicleRepository.getReferenceById(vehicleId);

        String headerAuth = request.getHeader("Authorization");

        String jwt= headerAuth.substring(7, headerAuth.length());

        //String jwt = parseJwt(request);

        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        User user = this.userRepository.findByUsername(username);
        //User user = this.userRepository.findByUsername();
        System.out.println("vehicle id is: "+vehicle);
        System.out.println("username is: "+username);
        Application application = new Application("submitted", vehicle, user);
        System.out.println("application is: "+application);
        this.applicationRepository.save(application);
        return application;
    }

}
