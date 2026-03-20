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


class RoomInventory {

    /**
     * Stores available room count for each room type.
     * Key -> Room type name
     * Value -> Available room count
     */
    private Map<String, Integer> roomAvailability;

    /**
     * Constructor initializes the inventory
     * with default availability values.
     */
    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Initializes room availability data.
     * This method centralizes inventory setup.
     */
    private void initializeInventory() {
        roomAvailability.put("Single", 10);
        roomAvailability.put("Double", 5);
        roomAvailability.put("Suite", 2);
    }

    /**
     * Returns the current availability map.
     *
     * @return map of room type to available count
     */
    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    /**
     * Updates availability for a specific room type.
     *
     * @param roomType the room type to update
     * @param count new availability count
     */
    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * MAIN CLASS - UseCase3InventorySetup
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Demonstrates centralized inventory usage.
 *
 * @version 3.1
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Create centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial availability
        System.out.println("Initial Room Availability:");
        System.out.println(inventory.getRoomAvailability());

        // Update availability
        inventory.updateAvailability("Single", 8);

        // Display updated availability
        System.out.println("\nUpdated Room Availability:");
        System.out.println(inventory.getRoomAvailability());
    }
}