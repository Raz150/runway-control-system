package com.airport.runway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airport.runway.model.Runway;
import com.airport.runway.services.RunwayServices;

@RestController
@RequestMapping("/runaway")
public class RunwayController { }
