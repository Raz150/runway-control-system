package com.airport.runway.services;

import com.airport.runway.enums.FlightStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightStatusService {
    public List<FlightStatus> getAllFlightStatus(){
        return Arrays.asList(FlightStatus.values());
    }
}
