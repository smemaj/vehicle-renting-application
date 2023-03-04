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
    @Column (name = "application_status", columnDefinition = "varchar(10) default 'submitted'")
    private String status;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date creationDate=new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
//    @JoinTable(	name = "rent_applications",
//            joinColumns = @JoinColumn(name = "application_id"),
//            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    public Application() {
    }

    public Application(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Application(String status, Date creationDate) {
        this.status = status;
        this.creationDate = creationDate;
    }

    public Application(String status, Vehicle vehicle, User user) {
        this.status = status;
        this.vehicle = vehicle;
        this.user = user;
    }

    public Application(String status, Date creationDate, Vehicle vehicle, User user) {
        this.status = status;
        this.creationDate = creationDate;
        this.vehicle = vehicle;
        this.user = user;
    }

    public Application(String status, Vehicle vehicle, User user, Date startDate, Date endDate) {
        this.status = status;
        this.vehicle = vehicle;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
