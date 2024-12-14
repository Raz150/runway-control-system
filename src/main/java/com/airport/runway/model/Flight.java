package com.airport.runway.model;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @ManyToOne // One plane can have multiple flight or vice-verse
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "runway_id")
    private Runway runway;

    private String flightStatus;
    private String departureFrom;
    private String arrivalAt;
    private String scheduledDeparture;
    private String scheduledArrival;

    public Flight(){}

    public Flight(Plane plane, Runway runway, String flightStatus, String departureFrom, String arrivalAt, String scheduledDeparture, String scheduledArrival){
        this.flightStatus = flightStatus;
        this.departureFrom = departureFrom;
        this.arrivalAt = arrivalAt;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
    }
}
