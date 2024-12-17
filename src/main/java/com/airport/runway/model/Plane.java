package com.airport.runway.model;

import jakarta.persistence.*;

import java.util.List;

import com.airport.runway.enums.PlaneModel;
import com.airport.runway.enums.PlaneType;

@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planeId;

    @OneToMany(mappedBy = "plane") // So we can fetch all flights records for a specific plane
    private List<Flight> flights;

    private PlaneType type;
    private PlaneModel model;
    private double fuelBalance;

    public Plane(){}

    public Plane(PlaneType type, PlaneModel model, double fuelBalance){
        this.type = type;
        this.model = model;
        this.fuelBalance = fuelBalance;
    }

    public PlaneType getType() {
        return type;
    }

    public void setType(PlaneType type) {
        this.type = type;
    }

    public PlaneModel getModel() {
        return model;
    }

    public void setModel(PlaneModel model) {
        this.model = model;
    }

    public double getFuelBalance() {
        return fuelBalance;
    }

    public void setFuelBalance(double fuelBalance) {
        this.fuelBalance = fuelBalance;
    }
}
