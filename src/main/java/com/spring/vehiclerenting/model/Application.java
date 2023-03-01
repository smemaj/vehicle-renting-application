package com.spring.vehiclerenting.model;

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
    private Date creationDate;

    @ManyToOne
//    @JoinTable(	name = "rent_applications",
//            joinColumns = @JoinColumn(name = "application_id"),
//            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Vehicle vehicle;

    @ManyToOne
    private User user;

    public Application() {
    }

    public Application(String status, Date creationDate) {
        this.status = status;
        this.creationDate = creationDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
