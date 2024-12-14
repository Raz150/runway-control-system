package com.airport.runway.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/table")
    public String index(Model model) {
        model.addAttribute("name", "Spring Boot");
        return "table"; // Refers to src/main/resources/templates/index.html
    }
}
