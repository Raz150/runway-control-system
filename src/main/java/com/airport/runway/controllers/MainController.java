package com.airport.runway.controllers;

import com.airport.runway.model.Flight;
import com.airport.runway.services.ArrivalService;
import com.airport.runway.services.FlightStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private ArrivalService arrivalService;
    private FlightStatusService flightStatusService;

    @Autowired
    public MainController(ArrivalService arrivalService, FlightStatusService flightStatusService) {
        this.arrivalService = arrivalService;
        this.flightStatusService = flightStatusService;
    }

    @GetMapping("/table")
    public String showTable(Model model) {
        Iterable<Map<String, Object>> arrivals = arrivalService.getFlightsOnArrival();


        List<Flight> secondTableFlights = flightStatusService.getFlightByTable("second");

        List<Flight> continuedFlights = new ArrayList<>();
        for (Flight flight:secondTableFlights){
            Flight newFlight = flightStatusService.continueExistingFlightData(flight.getFlightId());
            continuedFlights.add(newFlight);
        }

        model.addAttribute("arrivals", arrivals);
        model.addAttribute("runaways", continuedFlights);
        return "table";
    }
}