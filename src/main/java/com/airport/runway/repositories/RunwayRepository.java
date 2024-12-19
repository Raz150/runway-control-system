package com.airport.runway.repositories;

import com.airport.runway.model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunwayRepository extends JpaRepository<Runway, Long> {
    // Additional custom queries can be defined here if needed
}
