// HotelManagementSystem Class

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotelManagementSystem {
    private List<Hotel> hotels; // List to store all the hotels in the system

    // Constructor to initialize an empty list of hotels
    public HotelManagementSystem() {
        this.hotels = new ArrayList<>();
    }

    // Method to add a new hotel to the system
    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    // Getter method to retrieve the list of all hotels in the system
    public List<Hotel> getHotels() {
        return hotels;
    }

    // Method to find available rooms across all hotels for a given date range
    public List<Room> findAvailableRooms(Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Hotel hotel : hotels) {
            availableRooms.addAll(hotel.getAvailableRooms(startDate, endDate)); // Find available rooms in each hotel
                                                                                // add them to the list
        }
        return availableRooms;
    }

    // Method to make a reservation for a specific room in a chosen hotel
    public Reservation makeReservation(Hotel hotel, Room room, String customerName, Date startDate, Date endDate) {
        // Reserve the room in the hotel for the given dates
        hotel.reserveRoom(room, startDate, endDate);

        // Create a new Reservation object with details and return it
        return new Reservation(customerName, room, startDate, endDate);
    }
}
