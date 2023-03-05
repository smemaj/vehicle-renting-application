package com.spring.vehiclerenting.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String model;

    @NotNull
    private String year;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private VehicleType type;

    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "rent_applications",
//            joinColumns = @JoinColumn(name = "vehicle_id"),
//            inverseJoinColumns = @JoinColumn(name = "application_id"))
    private Set<Application> applications = new HashSet<>();

    public Vehicle() {
    }

//    public Vehicle(@NotNull String name, @NotNull String model, @NotNull String year) {
//        this.name = name;
//        this.model = model;
//        this.year = year;
//    }

    public Vehicle(Long id, String name, String model, String year, VehicleType type, Set<Application> applications) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.type = type;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
