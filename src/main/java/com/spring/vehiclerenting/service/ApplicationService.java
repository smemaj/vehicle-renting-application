package com.spring.vehiclerenting.service;

import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataRetrievalFailureException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ApplicationService {

    Application createApplication(Long vehicleId, Date startDate, Date endDate);


    void deleteApplication(Long applicationId);

    Set<Application> listApplications();
}
