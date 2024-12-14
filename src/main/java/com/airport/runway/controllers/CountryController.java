package com.airport.runway.controllers;

import com.airport.runway.enums.Country;
import com.airport.runway.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return countryService.getAllCountries();
    }
}
