// This class represents a reservation made for a specific room on certain dates

import java.util.Date;

public class Reservation {
    private String customerName; // Name of the customer who made the reservation
    private Room reservedRoom; // The room that was reserved
    private Date checkInDate; // Date on which the customer checks in
    private Date checkOutDate; // Date on which the customer checks out

    public Reservation(String customerName, Room reservedRoom, Date checkInDate, Date checkOutDate) {
        this.customerName = customerName;
        this.reservedRoom = reservedRoom;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getter method to retrieve the customer's name for this reservation
    public String getCustomerName() {
        return customerName;
    }

    // Getter method to get the room that was reserved
    public Room getReservedRoom() {
        return reservedRoom;
    }

    // Method to calculate the total price of the reservation based on the number of
    // days
    public double calculateTotalPrice() {
        // Calculate the number of days between check-in and check-out dates (in
        // milliseconds)
        long millisecondsBetween = checkOutDate.getTime() - checkInDate.getTime();

        // Convert milliseconds to days by dividing by the number of milliseconds in a
        // day (24 hours * 60 minutes * 60 seconds * 1000 milliseconds)
        long numDays = millisecondsBetween / (24 * 60 * 60 * 1000);

        // Calculate the total price by multiplying the number of days by the room's
        // price per night
        return numDays * reservedRoom.getPricePerNight();
    }
}
