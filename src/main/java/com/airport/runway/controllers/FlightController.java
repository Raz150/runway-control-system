package com.airport.runway.controllers;

import com.airport.runway.exceptions.RunwayExceptions;
import com.airport.runway.services.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airport.runway.model.Flight;
import com.airport.runway.services.ArrivalService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private ArrivalService arrivalService;
    private final FlightStatusService flightStatusService;

    public FlightController(FlightStatusService flightStatusService){
        this.flightStatusService = flightStatusService;
    }

    @PostMapping("/generate-flight")//http://localhost:8080/flight/generate-flight
    public Map<String, Object> generateArrival() { 
        Map<String, Object> newFlight = arrivalService.addFlightOnArrival();
        System.out.println("Flight incoming - " + newFlight);
        return newFlight;
    }

    // Display all flights
    @GetMapping("/display-incoming-flights")//http://localhost:8080/flight/display-incoming-flights
    public Iterable<Map<String, Object>> displayIncomingFlight() {
        Iterable<Map<String, Object>> flights = arrivalService.getFlightsOnArrival();
        return flights;
    }

    // Update flightStatus
    @PutMapping("/status/{flightId}")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long flightId){
        try {
            flightStatusService.updateStatus(flightId);
            return ResponseEntity.ok("Flight status updated");
        } catch (RunwayExceptions.RunwayAlreadyOccupiedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Get table by category which based on the flight status
    @GetMapping("/table/{category}")
    public List<Flight> getFlightsByTable(@PathVariable String category){
        return flightStatusService.getFlightByTable(category);
    }
}
