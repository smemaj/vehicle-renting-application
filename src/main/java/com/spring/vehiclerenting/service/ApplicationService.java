package com.spring.vehiclerenting.service;

import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.Role;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Set;

public interface ApplicationService {

    Application createApplication(Long vehicleId);
}
