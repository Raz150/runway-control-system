package com.airport.runway.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airport.runway.enums.FlightStatus;
import com.airport.runway.exceptions.RunwayExceptions;
import com.airport.runway.services.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.airport.runway.model.Flight;
import com.airport.runway.model.Runway;
import com.airport.runway.services.ArrivalService;

import org.springframework.http.ResponseEntity;

import com.airport.runway.repositories.FlightRepository;
import com.airport.runway.repositories.RunwayRepository;
import com.airport.runway.services.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private ArrivalService arrivalService;
    private final FlightStatusService flightStatusService;

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightService flightService;
    @Autowired
    private RunwayRepository runwayRepository;

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

    // Get second category flight table (since some need different data)
    @GetMapping("/table/category/second/{flightId}")
    public ResponseEntity<Flight> continueExistingFlightData(@PathVariable Long flightId){
        Flight continuedFlightData = flightStatusService.continueExistingFlightData(flightId);
        return ResponseEntity.ok(continuedFlightData);
    }

    // Get all secondCategoryData
    @GetMapping("/table/category/second")
    public ResponseEntity<List<Flight>> getAllFlightInSecondTable(){
        List<Flight> secondTableFlights = flightStatusService.getFlightByTable("second");

        List<Flight> continuedFlights = new ArrayList<>();
        for (Flight flight:secondTableFlights){
            Flight newFlight = flightStatusService.continueExistingFlightData(flight.getFlightId());
            continuedFlights.add(newFlight);
        }

        return ResponseEntity.ok(continuedFlights);
    }
    @PostMapping("/update-flight-status")
        public ResponseEntity<String> updateFlightStatus(@RequestBody Flight flight) {
        Flight existingFlight = flightRepository.findById(flight.getFlightId())
            .orElseThrow(() -> new RuntimeException("Flight not found"));
    
      // Get the current runway before changing the flight status
        Runway currentRunway = existingFlight.getRunway();
    
        existingFlight.setFlightStatus(flight.getFlightStatus());
    
    // If the flight status is TAKE_OFF, update runway availability
        if (flight.getFlightStatus() == FlightStatus.TAKE_OFF && currentRunway != null) {
            currentRunway.setAvailable(true);
            runwayRepository.save(currentRunway);
        }
       
    
        flightRepository.save(existingFlight);
        return ResponseEntity.ok("Flight status updated successfully");
}
}