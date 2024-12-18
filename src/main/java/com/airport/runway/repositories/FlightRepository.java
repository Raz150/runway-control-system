package com.airport.runway.repositories;

import com.airport.runway.enums.FlightStatus;
import com.airport.runway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
