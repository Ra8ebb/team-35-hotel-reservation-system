package com.hotel.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:hotel_system.db";
    /**
     * Establishes a connection to the SQLite database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Initializes the database by creating the necessary tables if they don't exist yet.
     */
    public static void initializeDatabase() {
        // SQL statement to create the Guests table
        String createGuestsTable = "CREATE TABLE IF NOT EXISTS guests ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT UNIQUE NOT NULL, "
                + "password TEXT NOT NULL, "
                + "dateOfBirth TEXT, "
                + "address TEXT, "
                + "gender TEXT, "
                + "roomPreference TEXT"
                + ");";

        // SQL statement to create the Rooms table
        String createRoomsTable = "CREATE TABLE IF NOT EXISTS rooms ("
                + "roomNo INTEGER PRIMARY KEY, "
                + "floorNo INTEGER, "
                + "roomType TEXT, "
                + "isAvailable BOOLEAN"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Execute the SQL commands
            stmt.execute(createGuestsTable);
            stmt.execute(createRoomsTable);
            System.out.println("[Database] Real SQLite Database initialized successfully.");

        } catch (SQLException e) {
            System.err.println("[Database] Initialization failed: " + e.getMessage());
        }
    }
}