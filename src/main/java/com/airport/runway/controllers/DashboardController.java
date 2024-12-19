package com.airport.runway.controllers;

import com.airport.runway.model.Flight;
import com.airport.runway.model.Runway;
import com.airport.runway.services.DashboardService;  // Correct service import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller  // Use @Controller for rendering web views (Thymeleaf or JSP)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;  // Correct service reference

    // Endpoint to get the dashboard view with model attributes
    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        // Retrieve list of runways and flights
        List<Runway> runways = dashboardService.getRunways();
        List<Flight> flights = dashboardService.getFlights();

        // Count available and occupied runways
        long freeRunways = runways.stream().filter(Runway::isAvailable).count();
        long occupiedRunways = runways.size() - freeRunways;
        Runway nextFreeRunway = runways.stream().filter(Runway::isAvailable).findFirst().orElse(null);

        // Add attributes to the model for use in the view
        model.addAttribute("availableRunways", runways);
        model.addAttribute("freeRunways", freeRunways);
        model.addAttribute("occupiedRunways", occupiedRunways);
        model.addAttribute("nextFreeRunway", nextFreeRunway);
        model.addAttribute("flights", flights);

        // Return the view name (Thymeleaf template or JSP)
        return "dashboard";  // View name for the dashboard page
    }
}
