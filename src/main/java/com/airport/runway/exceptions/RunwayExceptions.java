package com.airport.runway.exceptions;

public class RunwayExceptions extends RuntimeException {
    public RunwayExceptions(String message) {
        super(message);
    }

    public static class RunwayAlreadyOccupiedException extends RuntimeException {
        public RunwayAlreadyOccupiedException(String message) {
            super(message);
        }
    }
}
