/**
 * MAIN CLASS - UseCase2RoomInitialization
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * This class demonstrates object modeling using abstraction,
 * inheritance, and polymorphism for different hotel room types.
 *
 * Room availability is represented using simple variables
 * instead of data structures.
 *
 * @author Developer
 * @version 2.1
 */

public class HotelBookingApp {

    public static void main(String[] args) {

        // Static availability variables
        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 5;
        int suiteRoomAvailable = 2;

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        System.out.println("Welcome to Book My Stay - Room Availability\n");

        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable + "\n");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable + "\n");

        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable + "\n");
    }
}

/**
 * ABSTRACT CLASS - Room
 * Represents the base structure for all room types.
 */
abstract class Room {

    protected int beds;
    protected double size;
    protected double price;

    // Abstract method
    public abstract void displayRoomDetails();
}

/**
 * CLASS - SingleRoom
 */
class SingleRoom extends Room {

    public SingleRoom() {
        beds = 1;
        size = 200;
        price = 1000;
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: Single Room");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: ₹" + price);
    }
}

/**
 * CLASS - DoubleRoom
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        beds = 2;
        size = 350;
        price = 1800;
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: Double Room");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: ₹" + price);
    }
}

/**
 * CLASS - SuiteRoom
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        beds = 3;
        size = 600;
        price = 3500;
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type: Suite Room");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: ₹" + price);
    }
}