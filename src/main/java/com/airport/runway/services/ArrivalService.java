package com.airport.runway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airport.runway.model.Flight;
import com.airport.runway.repositories.FlightArrivalRepository;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ArrivalService {
    public ArrivalService() {}

    @Autowired FlightArrivalRepository flightArrivalRepository;
    @Autowired FlightService flightService;

    @Transactional
    public Map<String, Object> addFlightOnArrival() {
        System.out.println("addFlightOnArrival method called");
        Flight newFlight = flightService.generateFlightServiceData();
        Map<String, Object> flightDTO = flightArrivalRepository.save(newFlight).toDTOArrival();
        System.out.println("Flight saved with ID: " + newFlight.getFlightId());
        return flightDTO;
    }
    public Iterable<Map<String, Object>> getFlightsOnArrival() {
        Iterable<Flight> flights = flightArrivalRepository.findAll();
        List<Map<String, Object>> flightDTOs = new ArrayList<>();
        for (Flight flight : flights) {
            flightDTOs.add(flight.toDTOArrival());
        }
        return flightDTOs;
    }

}
