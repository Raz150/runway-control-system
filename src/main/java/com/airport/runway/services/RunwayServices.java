package com.airport.runway.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airport.runway.model.Flight;
import com.airport.runway.model.Runway;
import com.airport.runway.repositories.RunwayRepository;

import jakarta.transaction.Transactional;
@Service
public class RunwayServices {
    @Autowired
    RunwayRepository runawayRepository;
    @Autowired
    private FlightService flightService; 

    private static final int MAX_RUNWAYS = 8;

    public RunwayServices() {}

    //generating random runaway combination with a total sum of 8
    public List<Integer> generateRandomRunways(int MAX_RUNWAYS){
        Random random = new Random();
        // Generate the first number between 2 and 6
        int availableRunaway = random.nextInt(5) + 2; 
        int unavailableRunaway = MAX_RUNWAYS-availableRunaway; 
        return Arrays.asList(availableRunaway, unavailableRunaway);
    }

    //Simulating runway creation, we fix it to 8
    @Transactional
    public Runway runawayCreation() {
        List<Integer> runwayDistribution = generateRandomRunways(8);
        int availableRunways = runwayDistribution.get(0);
        int unavailableRunways = runwayDistribution.get(1);
    
        // Clear existing runways
        runawayRepository.deleteAll();
    
        for (int i = 0; i < availableRunways; i++) {
            // Available runways get an empty list
            Runway runway = new Runway(true);
            runawayRepository.save(runway);
        }
    
        for (int i = 0; i < unavailableRunways; i++) {
            Runway runway = new Runway(false);
            // toDTOArrival();
            Flight newFlight = flightService.generateFlightServiceDataForRunaway();
            newFlight.setRunway(runway);
            runway.addFlight(newFlight);
            System.out.println("Flight Generated: " + newFlight.toDTORunway());
    
            
            runawayRepository.save(runway);
        }
    
        System.out.println("Runway created: " + runawayRepository.findAll());
        return runawayRepository.findAll().get(0);
    }

    public List<Runway> displayAllRunways() {
        List<Runway> allRunways = runawayRepository.findAll();
        System.out.println("Displaying all runways:");
        
        for (Runway runway : allRunways) {
            System.out.println("Runway ID: " + runway.getRunwayId());
            System.out.println("Is Available: " + runway.isAvailable());
            
            // Fetch flights only if needed
            List<Flight> flights = runway.getFlights();
            if (flights != null && !flights.isEmpty()) {
                System.out.println("Associated Flights:");
                for (Flight flight : flights) {
                    System.out.println("   Flight ID: " + flight.getFlightId());
                }
            } else {
                System.out.println("No flights associated with this runway.");
            }
            System.out.println("-------------------");
        }
        
        return allRunways;
    }
 


}

