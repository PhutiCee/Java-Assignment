
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Hotel {
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

// This class represents a room within a hotel with its number, capacity, price,
// and availability information
class Room {
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

// This class represents a reservation made for a specific room on certain dates
class Reservation {
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

class HotelManagementSystem {
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

public class HotelProject {
    // Getting user input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Sample Hotels (replace with your actual hotel data)
        Hotel hotel1 = new Hotel("Gardens Hotel", "Gardens");
        hotel1.addRoom(new Room(101, 2, 100.00));
        hotel1.addRoom(new Room(102, 3, 120.00));
        Hotel hotel2 = new Hotel("City Center", "Polokwane");
        hotel2.addRoom(new Room(201, 1, 80.00));
        hotel2.addRoom(new Room(202, 2, 90.00));

        // Hotel Management System
        HotelManagementSystem hotelManagementSystem = new HotelManagementSystem();
        hotelManagementSystem.addHotel(hotel1);
        hotelManagementSystem.addHotel(hotel2);

        // User Interaction - Hotel Selection
        System.out.println("Available Hotels:");
        int hotelCount = 1;
        for (Hotel hotel : hotelManagementSystem.getHotels()) {
            System.out.println(hotelCount + ". " + hotel.getName());
            hotelCount++;
        }

        System.out.print("Enter hotel number to book (or 0 to exit): ");
        int chosenHotel = scanner.nextInt();

        // Process Hotel Selection
        if (chosenHotel > 0 && chosenHotel <= hotelCount) {
            Hotel chosenHotelObj = hotelManagementSystem.getHotels().get(chosenHotel - 1);
            System.out.println("\nDetails of " + chosenHotelObj.getName() + ":");

            // User Interaction - Dates
            System.out.println("Enter desired check-in date (YYYY-MM-DD): ");
            String checkInStr = scanner.nextLine();

            if (checkInStr.isEmpty()) {
                checkInStr = scanner.nextLine();
            }

            System.out.println("Enter desired check-out date (YYYY-MM-DD): ");
            String checkOutStr = scanner.nextLine();

            System.out.println("\nCheck-in: " + checkInStr + ", Check-out: " + checkOutStr);

            // Parse user input into Date objects
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            Date endDate = null;
            try {
                startDate = dateFormat.parse(checkInStr);
                endDate = dateFormat.parse(checkOutStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                System.out.println("Try booking again and enter right format of YYYY-MM-D example:2024-03-25");
            }

            // Process Hotel Selection (continued)
            if (startDate != null && endDate != null) {
                // User Interaction - Available Rooms
                System.out.println("\nAvailable Rooms:");
                System.out.println("------------------------------------------------------------");
                int roomCount = 1;
                List<Room> availableRooms = chosenHotelObj.getAvailableRooms(startDate, endDate);
                for (Room room : availableRooms) {
                    System.out.println(roomCount + ". Room Number: " + room.getNumber());
                    System.out.println("  Capacity: " + room.getCapacity());
                    System.out.println("  Price per Night: R" + room.getPricePerNight());
                    System.out.println("------------------------------------------------------------");
                    roomCount++;
                }

                // User Interaction - Choose Room
                System.out.print("\nEnter chosen room number: ");
                int chosenRoom = scanner.nextInt();

                // Validate Chosen Room
                Room chosenRoomObj = null;
                for (Room room : availableRooms) {
                    if (room.getNumber() == chosenRoom) {
                        chosenRoomObj = room;
                        break;
                    }
                }

                // Process Chosen Room
                if (chosenRoomObj != null) {
                    System.out.print("\nEnter customer name: ");
                    String customerName = scanner.nextLine();

                    if (customerName.isEmpty()) {
                        customerName = scanner.nextLine();
                    }

                    // Create Reservation
                    Reservation reservation = hotelManagementSystem.makeReservation(chosenHotelObj, chosenRoomObj,
                            customerName, startDate, endDate);

                    // Booking Confirmation Receipt
                    System.out.println("\n------------------------------------------------------------");
                    System.out.println("| Booking Confirmed!");
                    System.out.println("------------------------------------------------------------");
                    System.out.println("| Customer Name: " + reservation.getCustomerName());
                    System.out.println("| Room Number: " + reservation.getReservedRoom().getNumber());
                    System.out.println("|   Capacity: " + reservation.getReservedRoom().getCapacity());
                    System.out.println("|   Price per Night: R" + reservation.getReservedRoom().getPricePerNight());
                    System.out.println("|");
                    System.out.println("| Check-in Date: " + dateFormat.format(startDate));
                    System.out.println("| Check-out Date: " + dateFormat.format(endDate));
                    System.out.println("------------------------------------------------------------");
                    System.out.println("| Total Price: R" + reservation.calculateTotalPrice());
                    System.out.println("------------------------------------------------------------");
                } else {
                    System.out.println("Invalid room selection. Please try again.");
                }
            } else {
                System.out.println("Exiting...");
            }

        }
    }
}
