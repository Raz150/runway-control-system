package com.airport.runway.services;

import com.airport.runway.enums.Country;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {
    public List<Country> getAllCountries(){
        return Arrays.asList(Country.values());
    }
}
