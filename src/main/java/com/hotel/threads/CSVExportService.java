package com.hotel.threads;
import com.hotel.database.HotelDatabase;
import com.hotel.models.users.Guest;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CSVExportService implements Runnable {

    private boolean isRunning = true;
    private int exportIntervalMillis;

    public CSVExportService(int exportIntervalMillis) {
        this.exportIntervalMillis = exportIntervalMillis;
    }

    public void stopService() {
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("[Service] Background CSV Export Started...");

        while (isRunning) {
            try {
                Thread.sleep(exportIntervalMillis);
                generateGuestCSV();
            } catch (InterruptedException e) {
                System.out.println("[Service] CSV Export Service interrupted.");
                break;
            }
        }
    }

    private void generateGuestCSV() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "Guest_Report_" + timestamp + ".csv";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Username,Gender,Address,Room Preference\n");
            ArrayList<Guest> currentGuests = HotelDatabase.getGuests();
            for (Guest g : currentGuests) {
                if (g != null) {
                    writer.append(g.getUsername()).append(",")
                            .append(g.getGender().toString()).append(",")
                            .append(g.getAddress().replace(",", " ")).append(",")
                            .append(g.getRoomPreference().toString()).append("\n");
                }
            }
            System.out.println("[Service] Periodical CSV Generated Successfully: " + filename);

        } catch (IOException e) {
            System.err.println("Failed to write CSV file: " + e.getMessage());
        }
    }
}