package com.airport.runway.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.runway.model.Flight;
import com.airport.runway.services.ArrivalService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private ArrivalService arrivalService;

    @PostMapping("/generateflight2")
    public Map<String, Object> postGenerateFlight2() { 
        Map<String, Object> newFlight = arrivalService.addFlightOnArrival();
        System.out.println("Flight incoming - " + newFlight);
        return newFlight;
    }

    // Display all flights
    @GetMapping("/getallflights")
    public Iterable<Map<String, Object>> getAllFlights() {
        Iterable<Map<String, Object>> flights = arrivalService.getFlightsOnArrival();
        return flights;
    }
    // display flight by id


}