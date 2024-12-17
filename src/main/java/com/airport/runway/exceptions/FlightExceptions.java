package com.airport.runway.exceptions;

public class FlightExceptions extends RuntimeException {
    public FlightExceptions(String message) {
        super(message);
    }

    public static class FlightNotFoundException extends RuntimeException {
      public FlightNotFoundException(String message) {
        super(message);
      }
    }

    public static class InvalidFlightOperationException extends RuntimeException {
      public InvalidFlightOperationException(String message) {
        super(message);
      }
    }
}
