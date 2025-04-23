package org.iis2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DateEnhancedTest {
    
    @Test
    void givenValidDate_whenValidateWithException_thenNoExceptionIsThrown() {
        // Arrange
        Date validDate = new Date(15, 3, 2021);
        
        // Act & Assert
        assertDoesNotThrow(() -> validDate.validateWithException());
    }
    
    @Test
    void givenInvalidDate_whenValidateWithException_thenExceptionIsThrown() {
        // Arrange
        Date invalidDate = new Date(31, 2, 2021);
        
        // Act & Assert
        Exception exception = assertThrows(DateValidationException.class, 
            () -> invalidDate.validateWithException());
        assertTrue(exception.getMessage().contains("Invalid day"));
    }
    
    @Test
    void givenTwoDates_whenCompareWithIsAfter_thenCorrectResult() {
        // Arrange
        Date earlier = new Date(10, 5, 2020);
        Date later = new Date(11, 5, 2020);
        
        // Act & Assert
        assertTrue(later.isAfter(earlier));
        assertFalse(earlier.isAfter(later));
        assertFalse(earlier.isAfter(earlier)); // Same date
    }
    
    @Test
    void givenTwoDates_whenCompareWithIsBefore_thenCorrectResult() {
        // Arrange
        Date earlier = new Date(10, 5, 2020);
        Date later = new Date(11, 5, 2020);
        
        // Act & Assert
        assertTrue(earlier.isBefore(later));
        assertFalse(later.isBefore(earlier));
        assertFalse(earlier.isBefore(earlier)); // Same date
    }
    
    @Test
    void givenDate_whenPlusDays_thenCorrectNewDate() {
        // Arrange
        Date date = new Date(28, 2, 2020);
        
        // Act
        Date result = date.plusDays(2);
        
        // Assert
        assertEquals(1, result.getDay());
        assertEquals(3, result.getMonth());
        assertEquals(2020, result.getYear());
    }
    
    @Test
    void givenDate_whenPlusMonths_thenCorrectNewDate() {
        // Arrange
        Date date = new Date(31, 1, 2020);
        
        // Act
        Date result = date.plusMonths(1);
        
        // Assert
        assertEquals(29, result.getDay()); // February 2020 has 29 days
        assertEquals(2, result.getMonth());
        assertEquals(2020, result.getYear());
    }
    
    @Test
    void givenDate_whenPlusYears_thenCorrectNewDate() {
        // Arrange
        Date date = new Date(29, 2, 2020);
        
        // Act
        Date result = date.plusYears(1);
        
        // Assert
        assertEquals(29, result.getDay());
        assertEquals(2, result.getMonth());
        assertEquals(2021, result.getYear());
    }
    
    @Test
    void givenDate_whenFormat_thenCorrectlyFormatted() {
        // Arrange
        Date date = new Date(7, 5, 2020);
        
        // Act & Assert
        assertEquals("2020-05-07", date.format("yyyy-MM-dd"));
        assertEquals("07/05/2020", date.format("dd/MM/yyyy"));
        assertEquals("May 7, 2020", date.format("MMMM d, yyyy"));
    }
    
    @Test
    void givenEqualDates_whenEquals_thenTrue() {
        // Arrange
        Date date1 = new Date(15, 6, 2020);
        Date date2 = new Date(15, 6, 2020);
        
        // Act & Assert
        assertEquals(date1, date2);
        assertEquals(date1.hashCode(), date2.hashCode());
    }
    
    @Test
    void givenDifferentDates_whenEquals_thenFalse() {
        // Arrange
        Date date1 = new Date(15, 6, 2020);
        Date date2 = new Date(16, 6, 2020);
        
        // Act & Assert
        assertNotEquals(date1, date2);
    }
    
    @Test
    void givenDate_whenToString_thenCorrectFormat() {
        // Arrange
        Date date = new Date(7, 5, 2020);
        
        // Act & Assert
        assertEquals("2020-05-07", date.toString());
    }
}