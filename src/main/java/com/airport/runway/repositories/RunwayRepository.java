package com.airport.runway.repositories;

import com.airport.runway.model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunwayRepository extends JpaRepository<Runway, Long> {
}
