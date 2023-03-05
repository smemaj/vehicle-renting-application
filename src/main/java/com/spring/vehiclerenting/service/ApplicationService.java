package com.spring.vehiclerenting.service;

import com.spring.vehiclerenting.model.Application;
import java.util.Date;
import java.util.Set;

public interface ApplicationService {

    Application createApplication(Long vehicleId, Date startDate, Date endDate);

    Application updateApplicationDates(Long applicationId, Date startDate, Date endDate);

    void deleteApplication(Long applicationId);

    Set<Application> listApplications();
}
