package com.airport.runway.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Runway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runwayId;

    @OneToMany(mappedBy = "runway")
    private List<Flight> flights;

    private boolean isAvailable;

    public Runway(){}

    public Runway(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
