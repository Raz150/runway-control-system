package com.airport.runway.services;

import com.airport.runway.model.Flight;
import com.airport.runway.model.Runway;
import com.airport.runway.repositories.FlightDepartureRepository;
import com.airport.runway.repositories.RunwayRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final FlightDepartureRepository flightDepartureRepository;  // Injected repository for flights
    private final RunwayRepository runwayRepository;  // Injected repository for runways
    private final FlightService flightService;  // Service to generate Flight data

    // Constructor-based dependency injection
    public DashboardService(FlightDepartureRepository flightDepartureRepository,
                            RunwayRepository runwayRepository,
                            FlightService flightService) {
        this.flightDepartureRepository = flightDepartureRepository;
        this.runwayRepository = runwayRepository;
        this.flightService = flightService;
    }

    // Method to fetch all runways
    public List<Runway> getRunways() {
        return runwayRepository.findAll();  // Fetch all runway data from the repository
    }

    // Method to fetch all flights
    public List<Flight> getFlights() {
        return flightDepartureRepository.findAll();  // Fetch all flight data from the repository
    }

    // Method to add a flight on arrival
    @Transactional
    public Map<String, Object> addFlightOnArrival() {
        // Generate a new flight object (assuming the service method works correctly)
        Flight newFlight = flightService.generateFlightServiceData();

        if (newFlight == null) {
            throw new IllegalStateException("Flight data generation failed.");
        }

        // Save the new flight and convert it to a DTO
        return flightDepartureRepository.save(newFlight).toDTOArrival();
    }

    // Method to get flights on arrival, returning in DTO format
    public Iterable<Map<String, Object>> getFlightsOnArrival() {
        Iterable<Flight> flights = flightDepartureRepository.findAll();  // Fetch all flights
        List<Map<String, Object>> flightDTOs = new ArrayList<>();

        // Convert each flight to its DTO format
        for (Flight flight : flights) {
            flightDTOs.add(flight.toDTOArrival());
        }
        return flightDTOs;
    }

    // Method to generate a summary for the dashboard
    public Map<String, Object> generateDashboardSummary() {
        List<Runway> runways = getRunways();  // Fetch runways
        List<Flight> flights = getFlights();  // Fetch flights

        // Count available and occupied runways
        long freeRunways = runways.stream().filter(Runway::isAvailable).count();
        long occupiedRunways = runways.size() - freeRunways;

        // Get the next available runway
        Runway nextFreeRunway = runways.stream().filter(Runway::isAvailable).findFirst().orElse(null);

        // Build a map containing the summary data
        Map<String, Object> dashboardSummary = new HashMap<>();
        dashboardSummary.put("totalRunways", runways.size());
        dashboardSummary.put("freeRunways", freeRunways);
        dashboardSummary.put("occupiedRunways", occupiedRunways);
        dashboardSummary.put("nextFreeRunway", nextFreeRunway != null ? nextFreeRunway.getRunwayId() : "N/A");
        dashboardSummary.put("flights", flights);

        return dashboardSummary;  // Return the summary map
    }
}
