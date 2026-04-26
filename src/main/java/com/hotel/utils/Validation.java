import java.time.LocalDate;

public class Validation {

    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if (username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot contain spaces.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }


    public static void validateText(String text, String fieldName) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    public static void validateReservationDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }

        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past.");
        }

        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }
    }

    public static void validateDateOfBirth(LocalDate dateofbirth) {
        if (dateofbirth == null) {
            throw new IllegalArgumentException("Date of birth cannot be null.");
        }

        if (dateofbirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid date of birth.");
        }
    }

    public static void validateRoomNumber(int roomNumber) {
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be positive.");
        }
    }

    public static void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    public static void validatePayment(double amount, double balance) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment must be greater than zero.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
    }
}
