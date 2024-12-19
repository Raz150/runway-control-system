package com.airport.runway.services;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import com.airport.runway.exceptions.FlightExceptions;
import com.airport.runway.model.Flight;
import com.airport.runway.model.Plane;
import com.airport.runway.model.Runway;
import com.airport.runway.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FlightStatusService {
    private Flight flights;
    private final List<FlightStatus> flightStatuses;
    private final FlightRepository flightRepository;
    private RunwayServices runwayServices;
    private FlightService flightService;

    @Autowired
    public FlightStatusService(FlightRepository flightRepository, List<FlightStatus> flightStatuses, RunwayServices runwayServices, FlightService flightService){
        this.flightRepository = flightRepository;
        this.flightStatuses = flightStatuses;
        this.runwayServices = runwayServices;
        this.flightService = flightService;
    }

    // Method to change flight status sequentially
    public Flight updateStatus(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightExceptions.FlightNotFoundException("Flight not found"));

        // Get current status
        FlightStatus currentStatus = flight.getFlightStatus();

        // Assigning to the next status
        FlightStatus nextStatus = getNextStatus(currentStatus, flight);
        flight.setFlightStatus(nextStatus);
        return flightRepository.save(flight);
    }

    private FlightStatus getNextStatus(FlightStatus currentStatus, Flight flight){
        switch (currentStatus){
            case FLYING:
                return FlightStatus.REQUEST_LANDING;
            case REQUEST_LANDING:
                Runway assignedRunway = runwayServices.assignAvailableRunway();
                flight.setRunway(assignedRunway);
                flightRepository.save(flight);
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

    public List<Flight> getFlightByTable(String category){
        List<FlightStatus> statuses;

        switch (category){
            case "first":
                statuses = List.of(
                        FlightStatus.FLYING,
                        FlightStatus.REQUEST_LANDING
                );
                break;
            case "second":
                statuses = List.of(
                        FlightStatus.LANDING,
                        FlightStatus.LANDED,
                        FlightStatus.PARKED,
                        FlightStatus.DISEMBARK,
                        FlightStatus.REQUEST_TAKE_OFF
                );
                break;
            case "third":
                statuses = List.of(
                        FlightStatus.TAKE_OFF
                );
                break;
            default:
                throw new IllegalArgumentException("Invalid table category!");
        }

        return flightRepository.findByFlightStatusIn(statuses);
    }

    // Method to create new flight (second) table from existing data (first table)
    public Flight continueExistingFlightData(Long flightId){
        // Fetch existing data
        Flight existingFlight = flightRepository.findById(flightId)
                .orElseThrow(()-> new FlightExceptions.FlightNotFoundException("Flight not found!"));

        // Check if the flight belongs to the second category of flight status
        if (!isSecondCategoryFlightStatus(existingFlight.getFlightStatus())){
            throw new IllegalArgumentException("Invalid category");
        }

        // Cont. fetching existing data
        Plane plane = existingFlight.getPlane();
        Integer passenger = existingFlight.getPassenger();
        double fuelBalance = existingFlight.getPlane().getFuelBalance();
        LocalTime arrivalTime = existingFlight.getArrivalTime();
        Country arrivalFrom = existingFlight.getArrivingFrom();
        Runway runway = existingFlight.getRunway();

        // Create & save new flight data
        Flight newFlight = prepareData(existingFlight, plane, passenger, fuelBalance, arrivalFrom, arrivalTime, runway);
        System.out.println(newFlight);
        return flightRepository.save(newFlight);
//        return newFlight;
    }

    // Method to setup the data
    private Flight prepareData(Flight existingFlight, Plane plane, Integer passenger, double fuelBalance, Country arrivalFrom, LocalTime arrivalTime, Runway runway){
        Flight flight = new Flight();
        flight.setFlightId(existingFlight.getFlightId()); // Ensure flightId is the same and not creating a new one
        flight.setPlane(plane);
        flight.setFlightStatus(existingFlight.getFlightStatus());
        flight.setDepartureTo(flightService.generateRandomCountry()); // is actually DepartureTo
        flight.setScheduledDeparture(flightService.getCurrentTime());
        flight.setPassenger(passenger);
        flight.setArrivalTime(arrivalTime);
        flight.setArrivingFrom(arrivalFrom);
        flight.setRunway(runway);

        return flight;
    }

    // Handle secondCategoryFlightStatus
    private boolean isSecondCategoryFlightStatus(FlightStatus flightStatus){
        List<FlightStatus> secondCategoryFlightStatus = List.of(
            FlightStatus.LANDING,
            FlightStatus.LANDED,
            FlightStatus.PARKED,
            FlightStatus.DISEMBARK,
            FlightStatus.REQUEST_TAKE_OFF
        );

        return secondCategoryFlightStatus.contains(flightStatus);
    }
}
