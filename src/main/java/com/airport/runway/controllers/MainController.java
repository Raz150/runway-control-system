package com.airport.runway.controllers;

import com.airport.runway.model.Flight;
import com.airport.runway.services.ArrivalService;
import com.airport.runway.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ArrivalService arrivalService;

    @GetMapping("/table")
    public String showTable(Model model) {
        Iterable<Map<String, Object>> flights = arrivalService.getFlightsOnArrival();
        model.addAttribute("flights", flights);
        return "table";
    }
}