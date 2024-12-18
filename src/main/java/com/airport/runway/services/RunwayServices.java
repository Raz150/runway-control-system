package com.airport.runway.services;

import com.airport.runway.exceptions.RunwayExceptions;
import com.airport.runway.model.Runway;
import com.airport.runway.repositories.RunwayRepository;
import com.airport.runway.seeder.RunwaySeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunwayServices {
    private RunwayRepository runwayRepository;
    private RunwaySeeder runwaySeeder;

    @Autowired
    public RunwayServices(RunwayRepository runwayRepository, RunwaySeeder runwaySeeder){
        this.runwayRepository = runwayRepository;
        this.runwaySeeder = runwaySeeder;
    }

    // Search the first available runways
    public Runway assignAvailableRunway(){
        return runwayRepository.findFirstByIsAvailableTrue()
                .map(runway -> {
                    runway.setAvailable(false); // update the runway availability to false (occupied)
                    runwaySeeder.printRunwayAvailability();
                    return runwayRepository.save(runway);
                }).orElseThrow(()->new RunwayExceptions.RunwayAlreadyOccupiedException("No available runways!"));
    }
}

