package com.airport.runway.services;

import com.airport.runway.enums.FlightStatus;
import com.airport.runway.exceptions.FlightExceptions;
import com.airport.runway.model.Flight;
import com.airport.runway.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightStatusService {
    private Flight flights;
    private final List<FlightStatus> flightStatuses;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightStatusService(FlightRepository flightRepository, List<FlightStatus> flightStatuses){
        this.flightRepository = flightRepository;
        this.flightStatuses = flightStatuses;
    }

    // Method to change flight status sequentially
    public Flight updateStatus(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightExceptions.FlightNotFoundException("Flight not found"));

        // Get current status
        FlightStatus currentStatus = flight.getFlightStatus();
        FlightStatus nextStatus = getNextStatus(currentStatus);

        flight.setFlightStatus(nextStatus);
        return flightRepository.save(flight);

//        int currentStatusIndex = flightStatuses.indexOf(currentStatus);

        // Update the status if there's still next status
//        if (currentStatusIndex < flightStatuses.size() - 1) {
//            FlightStatus nextStatus = flightStatuses.get(currentStatusIndex + 1);
//            flight.setFlightStatus(nextStatus);
//            return flightRepository.save(flight);
//        }
//        return flight;
    }

    private FlightStatus getNextStatus(FlightStatus currentStatus){
        switch (currentStatus){
            case FLYING:
                return FlightStatus.REQUEST_LANDING;
            case REQUEST_LANDING:
                return FlightStatus.LANDING;
            case LANDING:
                return FlightStatus.LANDED;
            case LANDED:
                return FlightStatus.PARKED;
            case PARKED:
                return FlightStatus.DISEMBARK;
            case DISEMBARK:
                return FlightStatus.REQUEST_TAKE_OFF;
            case REQUEST_TAKE_OFF:
                return FlightStatus.TAKE_OFF;
            default:
                throw new IllegalStateException("Invalid flight status transition");
        }
    }
}
