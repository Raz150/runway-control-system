package com.airport.runway.repositories;

import com.airport.runway.model.Runway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RunwayRepository extends JpaRepository<Runway, Long> {
    Optional<Runway> findFirstByIsAvailableTrue();
}
