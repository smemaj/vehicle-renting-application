package com.spring.vehiclerenting.service;

import com.spring.vehiclerenting.errors.exception.CannotUpdateStatusException;
import com.spring.vehiclerenting.model.Application;
import com.spring.vehiclerenting.model.ApplicationStatus;

import java.util.Date;
import java.util.Set;

public interface ApplicationService {

    Application createApplication(Long vehicleId, Date startDate, Date endDate);

    Application updateApplicationDates(Long applicationId, Date startDate, Date endDate);

    void deleteApplication(Long applicationId);

    Set<Application> listApplications();

    Set<Application> filterByEndDate();

    Application updateApplicationStatusUser(Long applicationId, ApplicationStatus status) throws CannotUpdateStatusException;

    Application updateApplicationStatusAdm(Long applicationId, ApplicationStatus status) throws CannotUpdateStatusException;
}
