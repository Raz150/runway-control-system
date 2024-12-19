package com.airport.runway.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Runway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long runwayId;

    @OneToMany(mappedBy = "runway", cascade = CascadeType.PERSIST)//, cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Flight> flights = new ArrayList<>();

    private boolean isAvailable;

    public Runway() {}

    public Runway(boolean isAvailable) {
        this.isAvailable = isAvailable;
        this.flights = new ArrayList<>();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

     public List<Flight> getFlights() {
        if (flights == null) {
            flights = new ArrayList<>();
        }
        return flights;
     }
         // Add flights to this runway
    public void addFlight(Flight flight) {
        flight.setRunway(this); 
        this.flights.add(flight);
    }
     public long getRunwayId() {
         return runwayId;
     }
     public void setRunwayId(long runwayId) {
         this.runwayId = runwayId;
     }
     

}
