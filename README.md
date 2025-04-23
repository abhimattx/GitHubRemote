# Date Validator

A robust Java library for date validation and manipulation with a focus on clean code and comprehensive test coverage.

## Features

- **Precise Date Validation**: Validates calendar dates with specific constraints:
  - Year must be between 1900 and 2050
  - Month must be between 1 and 12
  - Day must be valid for the given month and year (accounting for leap years)
- **Exception-Based Validation**: Option to use exceptions for more detailed validation feedback
- **Date Comparison**: Methods to determine if one date is before or after another
- **Date Arithmetic**: Add days, months, or years to a date
- **Flexible Date Formatting**: Format dates in various string representations
- **Immutable Design**: Thread-safe implementation with consistent behavior
- **Well-Documented API**: Complete Javadoc documentation for all public methods
- **Comprehensive Test Suite**: Thorough test coverage for all functionality

## Usage Examples

### Basic Validation

```java
// Create a date object
Date date = new Date(29, 2, 2020);

// Validate the date
if (date.validate()) {
    System.out.println("Date is valid!");
} else {
    System.out.println("Date is invalid!");
}
```

### Exception-Based Validation

```java
Date date = new Date(31, 2, 2021);

try {
    date.validateWithException();
    System.out.println("Date is valid");
} catch (DateValidationException e) {
    System.out.println("Validation error: " + e.getMessage());
}
```

## Technical Details

- Built with Java 19
- No external runtime dependencies
- Uses JUnit 5 for unit testing
- Maven-based build system

## Getting Started

### Prerequisites

- Java 19 or higher
- Maven 3.6 or higher

## Building the Project

```bash
mvn clean install
```
## Running Tests
```bash
mvn test
```


## Project Structure

```
src/
├── main/java/org/iis2024/
│   ├── Date.java                # Core date implementation
│   └── DateValidationException.java # Custom exception class
└── test/java/org/iis2024/
    ├── DateTest.java            # Basic validation tests
    └── DateEnhancedTest.java    # Tests for enhanced functionality
```
## License

This project is licensed under the MIT License - see the LICENSE file for detail
```