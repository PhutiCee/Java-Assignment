// This class represents a hotel with its name, location, and a list of rooms.

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    private String name; // Name of the hotel
    private String location; // Location of the hotel (city, state, etc.)
    private List<Room> rooms; // List of rooms belonging to this hotel

    // Constructor to create a new Hotel object with a name and location
    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>(); // Initialize an empty list of rooms
    }

    // Method to add a room to the hotel's list of rooms
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Method to find available rooms within the hotel based on start and end dates
    public List<Room> getAvailableRooms(Date startDate, Date endDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(startDate, endDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    // Method to reserve a specific room in the hotel for given dates
    public void reserveRoom(Room room, Date startDate, Date endDate) {
        room.reserve(startDate, endDate);
    }

    // Getter method to get the hotel's name
    public String getName() {
        return name;
    }
}