package com.airport.runway.services;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import com.airport.runway.model.Flight;
import com.airport.runway.model.Plane;
import com.airport.runway.repositories.FlightArrivalRepository;
import com.airport.runway.repositories.FlightRepository;
import com.airport.runway.repositories.PlaneRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    
    @Autowired
    private PlaneService planeService;
    @Autowired
    private PlaneRepository planeRepository; 
    @Autowired
    private FlightRepository flightRepository;

    public FlightService() {}

    public Country generateRandomCountry() {
        Country[] countries = Country.values();
        int randomIndex = new Random().nextInt(countries.length);
        System.out.println("Country: " + countries[randomIndex]);
        return countries[randomIndex];
    }
    // -6 for arrival because we don't want to generate flight status for take off
    public FlightStatus generateRandomFlightStatusForArrival() {
        FlightStatus[] flightStatuses = FlightStatus.values();
        int randomIndex = new Random().nextInt(flightStatuses.length-6);
        System.out.println(" Flight status: " + flightStatuses[randomIndex]);
        return flightStatuses[randomIndex];
    }


    public LocalTime getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return currentTime.toLocalTime();
    }
    public Integer generateRandomPassengerCount() {
        Random random = new Random();
        return random.nextInt(266) + 10; // Generates a number between 10 and 275
    }

    @Transactional
    public Flight generateFlightServiceData() {
        Plane plane = planeService.generatePlaneServiceData();
        // Save the plane entity before associating it with a flight
        plane = planeRepository.save(plane); // Save the plane to the database

        Flight flight = new Flight(plane, generateRandomFlightStatusForArrival(), getCurrentTime(), generateRandomCountry(), generateRandomPassengerCount());
        System.out.println("\nFlight aDDED: " + flight);
        return flight;
    }

    public Flight getFlight(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight takeOff(Long id) {
        Flight flight = getFlight(id);
        if (flight != null && !flight.isTakenOff()) {
            flight.setTakenOff(true);
            flightRepository.save(flight); // Save flight with updated status
            deleteFlight(id);
            return flight;
        }
        return null; // If flight is already taken off or doesn't exist
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Flight flight = getFlight(id);
        if (flight != null) {
            flightRepository.delete(flight);  // Delete the flight
        }
    }
}
