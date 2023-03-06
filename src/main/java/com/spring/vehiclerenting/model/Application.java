package com.spring.vehiclerenting.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date creationDate=new Date();

    @ManyToOne()
    @JsonIgnore
    private Vehicle vehicle;

    @ManyToOne()
    @JsonIgnore
    private User user;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApplicationStatus status;

    public Application() {
    }

    public Application(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Application(Vehicle vehicle, User user, Date startDate, Date endDate, ApplicationStatus status) {
        this.vehicle = vehicle;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
