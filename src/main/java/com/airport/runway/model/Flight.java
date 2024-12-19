package com.airport.runway.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @ManyToOne
    @JoinColumn(name = "runway_id")  // Ensuring proper mapping for the runway relationship
    private Runway runway;

    private FlightStatus flightStatus;
    private Country departureFrom;
    private Country arrivingFrom;
    private LocalTime arrivalTime;
    private LocalTime scheduledDeparture;
    private LocalTime scheduledArrival;
    private Integer passenger;

    // Constructor for flight arrival
    public Flight(Plane plane, FlightStatus flightStatus, LocalTime arrivalTime, Country arrivingFrom, Integer passenger) {
        this.flightStatus = flightStatus;
        this.arrivalTime = arrivalTime;
        this.arrivingFrom = arrivingFrom;
        this.passenger = passenger;
    }

    // Default Constructor
    public Flight() {}

    // Getters and Setters
    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
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

    public Country getArrivingFrom() {
        return arrivingFrom;
    }

    public void setArrivingFrom(Country arrivingFrom) {
        this.arrivingFrom = arrivingFrom;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    // Method to convert Flight to DTO format for Arrival
    public Map<String, Object> toDTOArrival() {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("flightId", flightId);
        dto.put("runway", runway);
        dto.put("flightStatus", flightStatus);
        dto.put("arrivalTime", arrivalTime);
        dto.put("arrivingFrom", arrivingFrom);
        dto.put("passenger", passenger);
        return dto;
    }
}
