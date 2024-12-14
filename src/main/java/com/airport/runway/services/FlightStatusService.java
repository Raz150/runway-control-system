package com.airport.runway.services;

import com.airport.runway.enums.FlightStatus;
import com.airport.runway.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightStatusService {
    private Flight flight;
    private List<FlightStatus> flightStatuses = Arrays.asList(FlightStatus.values());

    @Autowired
    public FlightStatusService(Flight flight){
        this.flight = flight;
    }
//    public List<FlightStatus> getAllFlightStatus(){
//        return Arrays.asList(FlightStatus.values());
//    }

    // Method to change flight status sequently
    public void changeStatus(Flight flight){
        // Get current status
//        FlightStatus currentStatus = flight.get
    }
}
