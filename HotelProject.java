
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
