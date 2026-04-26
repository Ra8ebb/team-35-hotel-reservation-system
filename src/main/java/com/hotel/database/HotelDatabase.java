package com.hotel.database;

import com.hotel.models.users.*;
//import com.hotel.models.users.Staff;
//import com.hotel.models.users.Admin;
//import com.hotel.models.users.Receptionist;
import com.hotel.models.rooms.*;
//import com.hotel.models.rooms.RoomType;
//import com.hotel.models.rooms.Amenity;
import com.hotel.models.bookings.*;
//import com.hotel.models.bookings.Invoice;
import com.hotel.models.enums.Gender;
import com.hotel.models.enums.ReservationStatus;

import java.time.LocalDate;
import java.util.ArrayList;

public class HotelDatabase {

    // --- Static In-Memory Data Stores ---
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<Staff> staffMembers = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static ArrayList<RoomType> roomTypes = new ArrayList<>();
    private static ArrayList<Amenity> amenities = new ArrayList<>();

    // A flag to ensure we only seed the dummy data once
    private static boolean isInitialized = false;

    /**
     * Pre-populates the system with dummy data for immediate testing.
     * Call this method exactly once when your application starts in the Main class.
     */
    public static void initializeDummyData() {
        if (isInitialized) return;

        System.out.println("[Simulation] Initializing In-Memory Hotel Database with dummy data...");

        try {
            // 1. Create Room Types
            RoomType singleType = new RoomType(RoomType.TypeName.SINGLE, 100.0, 1);
            RoomType doubleType = new RoomType(RoomType.TypeName.DOUBLE, 150.0, 2);
            RoomType suiteType = new RoomType(RoomType.TypeName.SUITE, 300.0, 4);
            roomTypes.add(singleType);
            roomTypes.add(doubleType);
            roomTypes.add(suiteType);

            // 2. Create Amenities
            Amenity wifi = new Amenity("Free WiFi");
            Amenity tv = new Amenity("Smart TV");
            Amenity pool = new Amenity("Pool Access");
            amenities.add(wifi);
            amenities.add(tv);
            amenities.add(pool);

            // 3. Create Rooms
            Room room101 = new Room(101, 1, singleType, true);
            room101.addAmenity(wifi);
            room101.addAmenity(tv);

            Room room201 = new Room(201, 2, suiteType, true);
            room201.addAmenity(wifi);
            room201.addAmenity(tv);
            room201.addAmenity(pool);

            rooms.add(room101);
            rooms.add(room201);

            // 4. Create Staff (Admin & Receptionist)
            Admin mainAdmin = new Admin("admin1", "adminpass", LocalDate.of(1985, 5, 10), 8);
            Receptionist mainReceptionist = new Receptionist("recep1", "receppass", LocalDate.of(1995, 8, 20), 8);
            staffMembers.add(mainAdmin);
            staffMembers.add(mainReceptionist);

            // 5. Create Guests
            Guest dummyGuest1 = new Guest("johndoe", "pass123", LocalDate.of(1990, 1, 1),
                    "123 Main St", Gender.MALE, RoomType.TypeName.SINGLE);
            Guest dummyGuest2 = new Guest("janedoe", "pass456", LocalDate.of(1992, 4, 15),
                    "456 Oak Ave", Gender.FEMALE, RoomType.TypeName.SUITE);
            guests.add(dummyGuest1);
            guests.add(dummyGuest2);

            // 6. Create a Reservation
            Reservation dummyReservation = new Reservation(dummyGuest1, room101, LocalDate.now(), LocalDate.now().plusDays(3));
            dummyReservation.setStatus(ReservationStatus.CONFIRMED);
            room101.setAvailability(false); // Mark room as occupied
            reservations.add(dummyReservation);

            isInitialized = true;
            System.out.println("[Simulation] Database successfully initialized!\n");

        } catch (Exception e) {
            // This catches any of your custom exceptions (like InvalidGuestDataException)
            // if the dummy data violates your validation rules.
            System.err.println("[Simulation Error] Failed to load dummy data: " + e.getMessage());
        }
    }

    // --- Getters for the Database Lists ---
    // These return the actual list references so your controllers/managers can modify them

    public static ArrayList<Guest> getGuests() {
        return guests;
    }

    public static ArrayList<Staff> getStaffMembers() {
        return staffMembers;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public static ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public static ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public static ArrayList<Amenity> getAmenities() {
        return amenities;
    }
}