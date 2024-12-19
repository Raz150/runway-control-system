package com.airport.runway.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airport.runway.exceptions.RunwayExceptions;
import com.airport.runway.services.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/generate-flight")
    public Map<String, Object> postGenerateFlight2() { 
        Map<String, Object> newFlight = arrivalService.addFlightOnArrival();
        System.out.println("Flight incoming - " + newFlight);
        return newFlight;
    }

    // Display all flights
    @GetMapping("/display-incoming-flights")
    public Iterable<Map<String, Object>> getAllFlights() {
        Iterable<Map<String, Object>> flights = arrivalService.getFlightsOnArrival();
        return flights;
    }
    // display flight by id

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
}