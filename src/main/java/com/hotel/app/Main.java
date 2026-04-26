package com.hotel.app;
import com.hotel.database.DatabaseManager;
import com.hotel.database.HotelDatabase;
import com.hotel.threads.CSVExportService;
import com.hotel.models.users.Admin;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   HOTEL RESERVATION SYSTEM BOOTING UP    ");
        System.out.println("==========================================");

        // 1. Initialize Real SQLite Database
        DatabaseManager.initializeDatabase();

        // 2. Load Dummy Simulation Data
        HotelDatabase.initializeDummyData();

        // 3. Start the Background CSV Export Thread
        CSVExportService exportTask = new CSVExportService(15000);
        Thread exportThread = new Thread(exportTask);
        exportThread.setDaemon(true);
        exportThread.start();

        // 4. Test OOP Backend Logic
        try {
            System.out.println("\n--- Testing Backend Logic ---");
            Admin admin = (Admin) HotelDatabase.getStaffMembers().get(0);

            System.out.println("[Admin Task] Total Rooms in system: " + HotelDatabase.getRooms().size());
            System.out.println("[Admin Task] Total Guests in system: " + HotelDatabase.getGuests().size());

        } catch (Exception e) {
            System.err.println("An error occurred during backend testing: " + e.getMessage());
        }

        // 5. Keep app running
        System.out.println("\nSystem is running! Wait ~15 seconds to see the CSV generate.");
        System.out.println("Type 'exit' and press Enter to safely shut down.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                exportTask.stopService();
                break;
            } else {
                System.out.println("Unknown command. Type 'exit' to quit.");
            }
        }

        System.out.println("Shutting down... Goodbye!");
        scanner.close();
    }
}