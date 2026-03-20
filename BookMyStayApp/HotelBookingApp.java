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
import java.util.HashMap;
import java.util.Map;

// MAIN CLASS (only public class)
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Define room types
        Room singleRoom = new Room("Single", 2000);
        Room doubleRoom = new Room("Double", 3500);
        Room suiteRoom = new Room("Suite", 5000);

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform search (read-only)
        searchService.searchAvailableRooms(
                inventory,
                singleRoom,
                doubleRoom,
                suiteRoom
        );
    }
}

// SERVICE CLASS
class RoomSearchService {

    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.getOrDefault("Single", 0) > 0) {
            System.out.println("Single Room Available:");
            singleRoom.displayDetails();
            System.out.println("Available Count: " + availability.get("Single"));
            System.out.println("------------------------");
        }

        if (availability.getOrDefault("Double", 0) > 0) {
            System.out.println("Double Room Available:");
            doubleRoom.displayDetails();
            System.out.println("Available Count: " + availability.get("Double"));
            System.out.println("------------------------");
        }

        if (availability.getOrDefault("Suite", 0) > 0) {
            System.out.println("Suite Room Available:");
            suiteRoom.displayDetails();
            System.out.println("Available Count: " + availability.get("Suite"));
            System.out.println("------------------------");
        }

        if (availability.values().stream().allMatch(v -> v == 0)) {
            System.out.println("No rooms available at the moment.");
        }
    }
}

// ROOM CLASS
class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price per night: ₹" + price);
    }
}

// INVENTORY CLASS
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}