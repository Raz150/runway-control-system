package com.airport.runway.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeId;

    @OneToMany(mappedBy = "plane") // So we can fetch all flights records for a specific plane
    private List<Flight> flights;

    private String type;
    private String model;
    private double fuelBalance;

    public Plane(){}

    public Plane(String type, String model, double fuelBalance){
        this.type = type;
        this.model = model;
        this.fuelBalance = fuelBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFuelBalance() {
        return fuelBalance;
    }

    public void setFuelBalance(double fuelBalance) {
        this.fuelBalance = fuelBalance;
    }
}
