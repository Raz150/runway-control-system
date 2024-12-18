package com.airport.runway.model;

import java.time.LocalTime;

import java.util.LinkedHashMap;
import java.util.Map;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "runway_id")
    private Runway runway;

    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;
    @Enumerated(EnumType.STRING)
    private Country departureTo;
    @Enumerated(EnumType.STRING)
    private Country arrivingFrom;
    private LocalTime arrivalTime;
    private LocalTime scheduledDeparture;
    private LocalTime scheduledArrival;
    private Integer passenger;


    public Flight(){}

    public Flight(Plane plane, Runway runway, FlightStatus flightStatus, Country departureTo, LocalTime arrivalTime, LocalTime scheduledDeparture, LocalTime scheduledArrival){
        this.flightStatus = flightStatus;
        this.departureTo = departureTo;
        this.arrivalTime = arrivalTime;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
    }

    // For arrival
    public Flight(Plane plane, FlightStatus flightStatus, LocalTime arrivalTime, Country arrivingFrom, Integer passenger){
        this.plane = plane;
        this.flightStatus = flightStatus;
        this.arrivalTime = arrivalTime;
        this.arrivingFrom = arrivingFrom;
        this.passenger = passenger;
    }
        // For Departure/Runaway
    public Flight(Plane plane, Runway runway,  FlightStatus flightStatus, LocalTime scheduledDeparture, Country departureTo, Integer passenger){
        this.plane = plane;
        this.flightStatus = flightStatus;
        this.scheduledDeparture = scheduledDeparture;
        this.departureTo = departureTo;
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

    public Country getdepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(Country departureTo) {
        this.departureTo = departureTo;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Country getArrivingFrom() {
        return arrivingFrom;
    }

    public void setArrivingFrom(Country arrivingFrom) {
        this.arrivingFrom = arrivingFrom;

    }

    public LocalTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public LocalTime getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(LocalTime scheduledArrival) {
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
       // dto.put("runway", runway);
        dto.put("flightStatus", flightStatus);
        dto.put("arrivalTime", arrivalTime);
        dto.put("arrivingFrom", arrivingFrom);
        dto.put("passenger", passenger);
        return dto;
    }

    public Map<String, Object> toDTORunway() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("runway", runway);
        dto.put("flightId", flightId);
        dto.put("plane", plane);
        dto.put("flightStatus", flightStatus);
        dto.put("departureTo", departureTo);
        dto.put("scheduledDeparture", scheduledDeparture); 
        dto.put("passenger", passenger);
        return dto;
    }


}
