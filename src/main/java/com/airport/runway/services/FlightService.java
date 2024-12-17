package com.airport.runway.services;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import com.airport.runway.model.Flight;
import com.airport.runway.model.Plane;
import com.airport.runway.repositories.PlaneRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {
    
    @Autowired
    private PlaneService planeService;
    @Autowired
    private PlaneRepository planeRepository; 

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



}
