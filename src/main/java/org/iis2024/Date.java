package org.iis2024;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Represents a calendar date with validation capabilities.
 *
 * This class encapsulates a date specified by day, month, and year components. It offers
 * functionality to validate the date against specific rules: the year must be within the range of
 * 1900 to 2050, inclusive; the month must be within 1 to 12, inclusive; and the day must be valid
 * within the context of the specified month and year, taking into account variations for leap
 * years.
 *
 * Instances of this class are immutable, meaning that once a Date object is created, the day,
 * month, and year values cannot be changed.
 */
public class Date {
  private final int day;
  private final int month;
  private final int year;
  
  // Minimum and maximum valid years as constants
  private static final int MIN_YEAR = 1900;
  private static final int MAX_YEAR = 2050;

  /**
   * Retrieves the day component of this date.
   *
   * @return The day of the month (1-31).
   */
  public int getDay() {
    return day;
  }

  /**
   * Retrieves the month component of this date.
   *
   * @return The month of the year (1-12).
   */
  public int getMonth() {
    return month;
  }

  /**
   * Retrieves the year component of this date.
   *
   * @return The year (within the valid range of 1900 to 2050).
   */
  public int getYear() {
    return year;
  }

  /**
   * Constructs a Date object representing a specific day, month, and year.
   *
   * @param day The day of the month (1-31).
   * @param month The month of the year (1-12).
   * @param year The year (should ideally be within the valid range of 1900 to 2050 for validation
   *     to pass).
   */
  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Validates the date according to the predefined rules.
   *
   * @return true if the date is valid, false otherwise.
   */
  public boolean validate() {
    try {
      validateWithException();
      return true;
    } catch (DateValidationException e) {
      return false;
    }
  }

  /**
   * Validates the date according to the predefined rules and throws an exception if invalid.
   * The year must be within 1900 to 2050, the month within 1 to 12, and the day valid 
   * within the given month and year, considering leap years.
   *
   * @throws DateValidationException if the date is invalid, with a message explaining why
   */
  public void validateWithException() throws DateValidationException {
    // Check year range first
    if (year < MIN_YEAR || year > MAX_YEAR) {
      throw new DateValidationException("Year must be between " + MIN_YEAR + " and " + MAX_YEAR);
    }
    
    // Check month range
    if (month < 1 || month > 12) {
      throw new DateValidationException("Month must be between 1 and 12");
    }
    
    // Use SimpleDateFormat for day validation within month
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);
    String dateString = String.format("%d/%d/%d", day, month, year);

    try {
      dateFormat.parse(dateString);
    } catch (ParseException e) {
      throw new DateValidationException("Invalid day for the given month and year");
    }
  }
  
  /**
   * Compares this date with another date to determine if this date is after the specified date.
   *
   * @param other The date to compare with.
   * @return true if this date is after the specified date, false otherwise.
   */
  public boolean isAfter(Date other) {
    if (this.year > other.year) return true;
    if (this.year < other.year) return false;
    
    // Years are equal, check months
    if (this.month > other.month) return true;
    if (this.month < other.month) return false;
    
    // Years and months are equal, check days
    return this.day > other.day;
  }
  
  /**
   * Compares this date with another date to determine if this date is before the specified date.
   *
   * @param other The date to compare with.
   * @return true if this date is before the specified date, false otherwise.
   */
  public boolean isBefore(Date other) {
    if (this.year < other.year) return true;
    if (this.year > other.year) return false;
    
    // Years are equal, check months
    if (this.month < other.month) return true;
    if (this.month > other.month) return false;
    
    // Years and months are equal, check days
    return this.day < other.day;
  }
  
  /**
   * Returns a new Date object that represents a date that is the specified number of days after this date.
   *
   * @param days The number of days to add.
   * @return A new Date object representing the calculated date.
   */
  public Date plusDays(int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(year, month - 1, day); // Calendar months are 0-based
    calendar.add(Calendar.DAY_OF_MONTH, days);
    
    return new Date(
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.MONTH) + 1, // Convert back to 1-based month
        calendar.get(Calendar.YEAR)
    );
  }
  
  /**
   * Returns a new Date object that represents a date that is the specified number of months after this date.
   *
   * @param months The number of months to add.
   * @return A new Date object representing the calculated date.
   */
  public Date plusMonths(int months) {
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(year, month - 1, day); // Calendar months are 0-based
    calendar.add(Calendar.MONTH, months);
    
    return new Date(
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.MONTH) + 1, // Convert back to 1-based month
        calendar.get(Calendar.YEAR)
    );
  }
  
  /**
   * Returns a new Date object that represents a date that is the specified number of years after this date.
   *
   * @param years The number of years to add.
   * @return A new Date object representing the calculated date.
   */
  public Date plusYears(int years) {
    return new Date(day, month, year + years);
  }
  
  /**
   * Formats this date according to the specified pattern.
   *
   * @param pattern The pattern describing the date format (e.g., "yyyy-MM-dd").
   * @return A string representation of this date in the specified format.
   * @throws IllegalArgumentException if the pattern is invalid.
   */
  public String format(String pattern) {
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    formatter.setLenient(false);
    
    Calendar calendar = Calendar.getInstance();
    calendar.clear();
    calendar.set(year, month - 1, day);
    
    return formatter.format(calendar.getTime());
  }
  
  /**
   * Returns a string representation of this date in the format "yyyy-MM-dd".
   *
   * @return A string representation of this date.
   */
  @Override
  public String toString() {
    return format("yyyy-MM-dd");
  }
  
  /**
   * Compares this date with the specified object for equality.
   *
   * @param obj The object to compare with.
   * @return true if the specified object represents a date equal to this date, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    
    Date other = (Date) obj;
    return day == other.day &&
           month == other.month &&
           year == other.year;
  }
  
  /**
   * Returns a hash code value for this date.
   *
   * @return A hash code value for this date.
   */
  @Override
  public int hashCode() {
    return Objects.hash(day, month, year);
  }
}
