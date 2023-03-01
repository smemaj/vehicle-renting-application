package com.spring.vehiclerenting.security.service;

import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.Role;

import java.util.Set;

public interface ApplicationService {

    public Application createApplication(Long vehicleId);
}
