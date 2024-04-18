// This class represents a room within a hotel with its number, capacity, price,
// and availability information

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int number; // Room number (unique identifier)
    private int capacity; // Maximum number of guests allowed in the room
    private double pricePerNight; // Price charged per night for staying in this room
    private Map<Date, Boolean> availability; // Map to store availability information for specific dates (date:
                                             // isAvailable)

    // Constructor to create a new Room object with number, capacity, and price
    public Room(int number, int capacity, double pricePerNight) {
        this.number = number;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.availability = new HashMap<>(); // Initialize an empty map for availability
    }

    // Getter method to get the room number
    public int getNumber() {
        return number;
    }

    // Getter method to get the room's capacity
    public int getCapacity() {
        return capacity;
    }

    // Getter method to get the price per night for this room
    public double getPricePerNight() {
        return pricePerNight;
    }

    // Method to check if the room is available for a given date range (start and
    // end date)
    public boolean isAvailable(Date startDate, Date endDate) {
        for (Map.Entry<Date, Boolean> entry : availability.entrySet()) {
            Date date = entry.getKey();
            if (!date.before(startDate) && !date.after(endDate) && !entry.getValue()) {
                return false; // Room is not available if there's a conflict within the date range
            }
        }
        return true; // Room is available if no conflicts found for the date range
    }

    // Method to reserve the room for a specific date range, marking those dates as
    // unavailable
    public void reserve(Date startDate, Date endDate) {
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            availability.put(currentDate, false); // Mark the current date as unavailable
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Increment by one day
        }
    }
}
