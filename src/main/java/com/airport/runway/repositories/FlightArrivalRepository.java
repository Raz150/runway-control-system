package com.airport.runway.repositories;

import com.airport.runway.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightArrivalRepository extends JpaRepository<Flight, Long> {
    // Custom query to find a flight by its ID
    @Query("SELECT f FROM Flight f WHERE f.flightId = :flightId")
    Flight findFlightById(@Param("flightId") Long flightId);
}
