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

    public Runway() {}

    public Runway(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Long getRunwayId() {
        return runwayId;
    }

    public void setRunwayId(Long runwayId) {
        this.runwayId = runwayId;
    }
}
