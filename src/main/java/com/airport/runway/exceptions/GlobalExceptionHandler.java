package com.airport.runway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    Handle Flight Exceptions
    @ExceptionHandler(FlightExceptions.FlightNotFoundException.class)
    public ResponseEntity<String> handleFlightNotFound(FlightExceptions.FlightNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(FlightExceptions.InvalidFlightOperationException.class)
    public ResponseEntity<String> handleInvalidFlightOperation(FlightExceptions.InvalidFlightOperationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

//    Handle Runway Exceptions
    @ExceptionHandler(RunwayExceptions.RunwayAlreadyOccupiedException.class)
    public ResponseEntity<String> handleRunwayAlreadyOccupied(RunwayExceptions.RunwayAlreadyOccupiedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
