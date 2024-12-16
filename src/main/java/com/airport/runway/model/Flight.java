package com.airport.runway.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights")
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

    private FlightStatus flightStatus;
    private Country departureFrom;
    private String arrivalTime;
    private Country arrivingFrom;
    private String scheduledDeparture;
    private String scheduledArrival;
    private Integer passenger;
    

    public Flight(){}

    public Flight(Plane plane, Runway runway, FlightStatus flightStatus, Country departureFrom, String arrivalTime, String scheduledDeparture, String scheduledArrival){
        this.flightStatus = flightStatus;
        this.departureFrom = departureFrom;
        this.arrivalTime = arrivalTime;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
    }
    // For arrival
    public Flight(Plane plane, FlightStatus flightStatus, String arrivalTime, Country arrivingFrom, Integer passenger){
        this.plane = plane;
        this.flightStatus = flightStatus;
        this.arrivalTime = arrivalTime;
        this.arrivingFrom = arrivingFrom;
        this.passenger = passenger;
    }
    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Country getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(Country departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Country getArrivingFrom() {
        return arrivingFrom;
    }

    public void setArrivingFrom(Country arrivingFrom) {
        this.arrivingFrom = arrivingFrom;
    }

    public String getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(String scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public String getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(String scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public Integer getPassenger() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    // Method ensure that arrival flight only shows what we need to show, can add and remove as needed here.
    public Map<String, Object> toDTOArrival() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("flightId", flightId);
        dto.put("plane", plane);
        dto.put("runway", runway);
        dto.put("flightStatus", flightStatus);
        dto.put("arrivalTime", arrivalTime);
        dto.put("arrivingFrom", arrivingFrom);
        dto.put("passenger", passenger);
        return dto;
    }


}
