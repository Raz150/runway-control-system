package com.airport.runway.seeder;

import com.airport.runway.model.Runway;
import com.airport.runway.repositories.RunwayRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RunwaySeeder implements CommandLineRunner {
    private RunwayRepository runwayRepository;

    public RunwaySeeder(RunwayRepository runwayRepository){
        this.runwayRepository = runwayRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        // Initialize random number of runway (max10)
//        int numRunway = random.nextInt(10)+1;

        for (int i = 1; i <= 5; i++){
            Runway runway = new Runway();
            runway.setAvailable(random.nextBoolean());
            runwayRepository.save(runway);
        }

//        System.out.println("Initialized "+numRunway+" runways");
        System.out.println("Initialized random runways availability");
        printRunwayAvailability();
    }

    public void printRunwayAvailability(){
        List<Runway> allRunways = runwayRepository.findAll();

        int availableCounter = 0;
        int unavailableCounter = 0;

        for (Runway runway:allRunways){
            if (runway.isAvailable()){
                availableCounter++;
            } else {
                unavailableCounter++;
            }
        }

        System.out.println("Available runways: "+availableCounter);
        System.out.println("Not available runways: "+unavailableCounter);
    }
}
