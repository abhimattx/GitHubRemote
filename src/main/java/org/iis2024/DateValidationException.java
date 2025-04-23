package org.iis2024;

/**
 * Exception thrown when date validation fails.
 * This exception provides specific information about why a date is considered invalid.
 */
public class DateValidationException extends Exception {
    
    /**
     * Constructs a new DateValidationException with the specified message.
     * 
     * @param message The detail message explaining the validation failure.
     */
    public DateValidationException(String message) {
        super(message);
    }
}