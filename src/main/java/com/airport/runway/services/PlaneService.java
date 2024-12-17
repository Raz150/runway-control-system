package com.airport.runway.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.airport.runway.enums.Country;
import com.airport.runway.enums.FlightStatus;
import com.airport.runway.enums.PlaneModel;
import com.airport.runway.enums.PlaneType;
import com.airport.runway.model.Plane;
@Service
public class PlaneService {
    private Plane plane;
    public PlaneService() {}
    public PlaneModel generateRandomPlaneModel() {
        PlaneModel[] planeModels = PlaneModel.values();
        int randomIndex = new Random().nextInt(planeModels.length);
        System.out.println("Plane model: " + planeModels[randomIndex]);
        return planeModels[randomIndex];
    }

    public PlaneType generateRandomPlaneType() {
        PlaneType[] planeTypes = PlaneType.values();
        int randomIndex = new Random().nextInt(planeTypes.length);
        System.out.println("Plane type: " + planeTypes[randomIndex]);
        return planeTypes[randomIndex];
    }

    public double generateFuelBalanceAtArrival(){
        double fuelBalance = (long) (Math.random() * (65 - 15 + 1)) + 15;
        System.out.println("Fuel balance at arrival: "  + fuelBalance);
        return fuelBalance;
    }

    public Plane generatePlaneServiceData() {
        plane = new Plane();
        plane.setType(generateRandomPlaneType());
        plane.setModel(generateRandomPlaneModel());
        plane.setFuelBalance(generateFuelBalanceAtArrival());
        System.out.println("\nPlane: " + plane);
        return plane;
    }    

}
