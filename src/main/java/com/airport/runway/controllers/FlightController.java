package com.airport.runway.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airport.runway.services.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airport.runway.model.Flight;
import com.airport.runway.services.ArrivalService;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private ArrivalService arrivalService;
    private final FlightStatusService flightStatusService;

    public FlightController(FlightStatusService flightStatusService){
        this.flightStatusService = flightStatusService;
    }

    @PostMapping("/generateflight")
    public Map<String, Object> postGenerateFlight2() { 
        Map<String, Object> newFlight = arrivalService.addFlightOnArrival();
        System.out.println("Flight incoming - " + newFlight);
        return newFlight;
    }

    // Display all flights
    @GetMapping("/displayincomingflights")
    public Iterable<Map<String, Object>> getAllFlights() {
        Iterable<Map<String, Object>> flights = arrivalService.getFlightsOnArrival();
        return flights;
    }
    // display flight by id

    // Update flightStatus
    @PutMapping("/status/{flightId}")
    public ResponseEntity<Flight> updateStatus(
            @PathVariable Long flightId){
        Flight updatedFlight = flightStatusService.updateStatus(flightId);
        return ResponseEntity.ok(updatedFlight);
    }
}