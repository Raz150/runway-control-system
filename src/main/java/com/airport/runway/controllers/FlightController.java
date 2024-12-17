package com.airport.runway.controllers;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airport.runway.services.ArrivalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private ArrivalService arrivalService;

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


}