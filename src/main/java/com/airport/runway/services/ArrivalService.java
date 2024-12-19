package com.airport.runway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.runway.model.Flight;
import com.airport.runway.repositories.FlightDepartureRepository;  // Correct repository import

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArrivalService {

    @Autowired
    FlightDepartureRepository flightDepartureRepository;  // Use FlightDepartureRepository instead of FlightArrivalRepository

    @Autowired
    FlightService flightService;  // Make sure this service generates Flight objects correctly

    @Transactional
    public Map<String, Object> addFlightOnArrival() {
        System.out.println("addFlightOnArrival method called");
        // Assuming generateFlightServiceData() creates and returns a valid Flight object
        Flight newFlight = flightService.generateFlightServiceData();
        // Save the new flight and convert it to DTO
        Map<String, Object> flightDTO = flightDepartureRepository.save(newFlight).toDTOArrival();
        System.out.println("Flight saved with ID: " + newFlight.getFlightId());
        return flightDTO;
    }

    public Iterable<Map<String, Object>> getFlightsOnArrival() {
        Iterable<Flight> flights = flightDepartureRepository.findAll();  // Use the correct repository
        List<Map<String, Object>> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(flight.toDTOArrival());  // Convert to DTO format
        }
        return flightDTOs;
    }
}
