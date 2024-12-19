package com.airport.runway.repositories;

import com.airport.runway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDepartureRepository extends JpaRepository<Flight, Long> {
    // Add custom queries if needed
}
