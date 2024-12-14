package com.airport.runway.services;

import com.airport.runway.enums.FlightStatus;
import com.airport.runway.model.Flight;
import com.airport.runway.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightStatusService {
    private Flight flight;
    private FlightRepository flightRepository;
    private List<FlightStatus> flightStatuses = Arrays.asList(FlightStatus.values());

    @Autowired
    public FlightStatusService(Flight flight, FlightRepository flightRepository){
        this.flight = flight;
        this.flightRepository = flightRepository;
    }

//    public List<FlightStatus> getAllFlightStatus(){
//        return Arrays.asList(FlightStatus.values());
//    }

    // Method to change flight status sequently
    public void changeStatus(Flight flight){
        // Get current flightStatus
        FlightStatus currentFlightStatus = FlightStatus.valueOf(flight.getFlightStatus());

        // Get current flightStatus index
        int currentStatusIndex = flightStatuses.indexOf(currentFlightStatus);

        // Updating the status to the next one (only if it's not the last status)
        try {
            if (currentStatusIndex >= 0 && currentStatusIndex < flightStatuses.size()-1){
                FlightStatus nextStatus = flightStatuses.get(currentStatusIndex+1);
                updateFlightStatus(flight, nextStatus);
            } else {
                throw new IndexOutOfBoundsException("No more status!");
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    // Update the flight status into db
    private void updateFlightStatus(Flight flight, FlightStatus flightStatus){
        flight.setFlightStatus(String.valueOf(flightStatus));
        flightRepository.save(flight);
    }
}
