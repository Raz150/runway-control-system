package com.airport.runway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/table")
    public String showTable(Model model) {
        model.addAttribute("name", "John Doe");
        // Optional: Add title directly to the model if needed
        model.addAttribute("pageTitle", "Table Page");
        return "table";
    }
}