import java.util.ArrayList;
import java.util.Scanner;
public class HotelManagementSystem {

    public static Scanner input;
    private static final double SINGLE_ROOM_PRICE = 20.0;
    private static final double DOUBLE_ROOM_PRICE = 30.0;
    private static double totalIncome = 0.0;

    //Global scanner that can be used anywhere.
    public static void main(String[] args) {

        input = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Management System!");

        //Input loop.
        for (; ; ) {

            while (true) {
                printOptions();

                try {

                    int option = Integer.parseInt(input.nextLine());

                    //Process the different options.
                    switch (option) {
                        case 1 -> {
                            checkRoom();
                            System.out.println();
                        }
                        case 2 -> {
                            makeReservation();
                            System.out.println();
                        }
                        case 3 -> {
                            checkIn();
                            System.out.println();
                        }
                        case 4 -> {
                            checkOut();
                            System.out.println();
                        }
                        case 5 -> {
                            searchGuest();
                            System.out.println();
                        }
                        case 6 -> {
                            reports();
                            System.out.println();
                        }
                        case 7 -> {
                            System.out.println();
                            System.out.println("Terminating program...");
                            System.out.println("Thanks for using the Hotel Management System!");
                            System.exit(0);
                        }
                        default -> {
                            System.out.println("Invalid Choice. Please enter a valid option.");
                            System.out.println();
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    System.out.println();
                }
            }

        }
    }

    // Method to validate date format
    public static Date parseDate(String dateString) throws IllegalArgumentException {
        String[] parts = dateString.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Use dd-mm-yyyy.");
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return new Date(day, month, year);
    }
    public static ArrayList<Room> rooms = new ArrayList<Room>() {
        {
            add(new Room(101, "Single"));
            add(new Room(102, "Single"));
            add(new Room(103, "Single"));
            add(new Room(201, "Double"));
            add(new Room(202, "Double"));
            add(new Room(203, "Double"));
        }
    };
    //Handles user input to check for room availability.
    public static void checkRoom() {
        System.out.println("Room Status:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }
    public static boolean isValidEmail(String email) {
        // Simple email format validation
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
    private static boolean containsNumbers(String text) {
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    //Handles user input to make a reservation.
    public static void makeReservation() {
        Room selectedRoom = null;
        while (selectedRoom == null) {
            System.out.print("Enter the room number you want to reserve: ");
            try {
                int selectedRoomNumber = Integer.parseInt(input.nextLine());

                // Find the selected room
                for (Room room : rooms) {
                    if (room.getRoomNumber() == selectedRoomNumber && room.isAvailable()) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom == null) {
                    System.out.println("Invalid room number or the room is not available. Please enter again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid room number.");
            }
        }

        Date checkInDate = null;
        while (checkInDate == null) {
            System.out.print("Enter check-in date (dd-mm-yyyy): ");
            String checkInInput = input.nextLine();
            try {
                checkInDate = parseDate(checkInInput);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Date checkOutDate = null;
        while (checkOutDate == null || checkOutDate.isBefore(checkInDate)) {
            System.out.print("Enter check-out date (dd-mm-yyyy): ");
            String checkOutInput = input.nextLine();
            try {
                checkOutDate = parseDate(checkOutInput);
                if (checkOutDate.isBefore(checkInDate)) {
                    System.out.println("Error: Check-out date is before check-in date");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String guestName = "";
        while (guestName.isEmpty() || containsNumbers(guestName)) {
            System.out.print("Enter guest name: ");
            guestName = input.nextLine();

            if (guestName.isEmpty()) {
                System.out.println("Guest name cannot be empty. Please enter a valid name.");
            } else if (containsNumbers(guestName)) {
                System.out.println("Guest name cannot contain numbers. Please enter a valid name.");
            }
        }

        String guestEmail = "";
        while (!isValidEmail(guestEmail)) {
            System.out.print("Enter guest email: ");
            guestEmail = input.nextLine();
            if (!isValidEmail(guestEmail)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }
        Guest guest = new Guest(guestName);

        // Create a Reservation object
        Reservation reservation = new Reservation(guest, selectedRoom, checkInDate, checkOutDate);

        // Set the reservation for the selected room
        selectedRoom.setReservation(reservation);
        System.out.println("Check-in successful : " + guestName);
    }
    //Handles user input to check-in a guest.
    public static void checkIn() {
        System.out.println("Enter the room number to check-in: ");
        int roomNumber = Integer.parseInt(input.nextLine());

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getAvailability() && !room.getOccupied()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {

            String guestName = "";
            while (guestName.isEmpty() || containsNumbers(guestName)) {
                System.out.print("Enter guest name: ");
                guestName = input.nextLine();

                if (guestName.isEmpty()) {
                    System.out.println("Guest name cannot be empty. Please enter a valid name.");
                } else if (containsNumbers(guestName)) {
                    System.out.println("Guest name cannot contain numbers. Please enter a valid name.");
                }
            }

            // Create a Guest object with name and email
            Guest guest = new Guest(guestName);

            // Assign the guest to the room
            selectedRoom.setOccupant(guest);
            selectedRoom.setOccupied(true);
            selectedRoom.setAvailability(false);

            System.out.println("Check-in successful. Guest: " + guest);
        } else {
            System.out.println("Invalid room number or the room is not available for check-in.");
        }
    }

    //Handles user input to check-out a guest.
    public static void checkOut() {
        System.out.print("Enter the room number to check-out: ");
        int roomNumber = Integer.parseInt(input.nextLine());

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.getOccupied()) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null) {
            if (selectedRoom.getReservation() == null || selectedRoom.getReservation().getCheckInDate() == null) {
                int totalNights = 0;
                while (totalNights <= 0) {
                    try {
                        System.out.print("Enter total nights stayed: ");
                        totalNights = Integer.parseInt(input.nextLine());
                        if (totalNights <= 0) {
                            System.out.println("Total nights must be greater than 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }

                double totalPrice = calculateTotalPrice(selectedRoom, selectedRoom.getType().equalsIgnoreCase("Single") ? SINGLE_ROOM_PRICE : DOUBLE_ROOM_PRICE, totalNights);

                totalIncome += totalPrice;

                selectedRoom.setOccupant(null);
                selectedRoom.setOccupied(false);
                selectedRoom.setAvailability(true);

                // Set the room's price to 0
                selectedRoom.setPrice(0.0);

                System.out.println("Check-out successful. Room " + roomNumber + " is now available.");
                System.out.println("Total Price: $" + totalPrice);
            } else {
                double totalPrice = calculateTotalPrice(selectedRoom, selectedRoom.getType().equalsIgnoreCase("Single") ? SINGLE_ROOM_PRICE : DOUBLE_ROOM_PRICE);

                totalIncome += totalPrice;

                selectedRoom.setOccupant(null);
                selectedRoom.setOccupied(false);
                selectedRoom.setAvailability(true);

                // Set the room's price to 0
                selectedRoom.setPrice(0.0);

                System.out.println("Check-out successful. Room " + roomNumber + " is now available.");
                System.out.println("Total Price: $" + totalPrice);
            }
        } else {
            System.out.println("Invalid room number or the room is not occupied.");
        }
    }

    private static double calculateTotalPrice(Room room, double basePrice, int totalNights) {
        return basePrice * totalNights;
    }

    private static double calculateTotalPrice(Room room, double basePrice) {
        Reservation reservation = room.getReservation();
        Date checkInDate = reservation.getCheckInDate();
        Date checkOutDate = reservation.getCheckOutDate();

        int nights = calculateNightDifference(checkInDate, checkOutDate);
        return calculateTotalPrice(room, basePrice, nights);
    }
    private static int calculateNightDifference(Date checkInDate, Date checkOutDate) {
        // Assuming that the Date class has a method to calculate the difference in days
        return checkOutDate.differenceInDays(checkInDate);
    }


    //Handles user input to search for a guest.
    public static void searchGuest() {
        System.out.print("Enter guest name or room number to search: ");
        String searchQuery = input.nextLine();

        boolean guestFound = false;

        for (Room room : rooms) {
            Guest guest = room.getOccupant();
            if (guest != null && (guest.getName().equalsIgnoreCase(searchQuery) || String.valueOf(room.getRoomNumber()).equals(searchQuery))) {
                System.out.println("Guests Found:");
                System.out.println("Guest Name: " + guest.getName() + ", Room Number: " + room.getRoomNumber());
                guestFound = true;
            }
        }

        if (!guestFound) {
            System.out.println("No guests found matching the search criteria.");
        }
    }

    //Handles user input to generate reports.
    public static void reports() {
        System.out.println("1. List of all rooms");
        System.out.println("2. Occupied rooms");
        System.out.println("3. Available rooms");
        System.out.println("4. Guest occupancy report");
        System.out.println("5. Total Income earned");
        System.out.print("Enter your choice: ");
        int reportOption = Integer.parseInt(input.nextLine());

        switch (reportOption) {
            case 1:
                listAllRoomsReport();
                break;
            case 2:
                occupiedRoomsReport();
                break;
            case 3:
                availableRoomsReport();
                break;
            case 4:
                guestOccupancyReport();
                break;
            case 5:

                System.out.println("Total income earned $" + totalIncome);
                break;
            default:
                System.out.println("Invalid report option.");
                break;
        }
    }

    public static void listAllRoomsReport() {
        System.out.println("List of all rooms:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public static void occupiedRoomsReport() {
        System.out.println("Occupied rooms:");
        for (Room room : rooms) {
            if (room.getOccupied()) {
                System.out.println(room);
            }
        }
    }

    public static void availableRoomsReport() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (room.getAvailability()) {
                System.out.println(room);
            }
        }
    }

    public static void guestOccupancyReport() {
        System.out.println("Guest occupancy report:");
        for (Room room : rooms) {
            if (room.getOccupied()) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Occupant: " + room.getOccupant().getName());
            }
        }
    }
    public static void printOptions() {
        System.out.println("\n1. Check room availability");
        System.out.println("2. Make a reservation");
        System.out.println("3. Check-in a guest");
        System.out.println("4. Check-out a guest");
        System.out.println("5. Search for a guest");
        System.out.println("6. Generate reports");
        System.out.println("7. Exit");
        System.out.println(" ");
        System.out.print("Please select an option: ");
    }
}
