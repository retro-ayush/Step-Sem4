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
import java.io.*;
import java.util.*;

// RoomInventory class (same as previous use cases)
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void setRoomCount(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getRoomCount(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllRooms() {
        return inventory;
    }

    public void showInventory() {
        System.out.println("Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + ", Available: " + entry.getValue());
        }
    }
}

// Service to persist and recover inventory from a file
public class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     *
     * Each line follows the format: roomType=availableCount
     *
     * @param inventory centralized room inventory
     * @param filePath  path to persistence file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getAllRooms().entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory successfully saved to " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    /**
     * Loads room inventory state from a file.
     *
     * @param inventory centralized room inventory
     * @param filePath  path to persistence file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Persistence file not found: " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("=")) continue;
                String[] parts = line.split("=");
                if (parts.length != 2) continue;
                String roomType = parts[0].trim();
                int count = Integer.parseInt(parts[1].trim());
                inventory.setRoomCount(roomType, count);
            }
            System.out.println("Inventory successfully loaded from " + filePath);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }
}

// Main class demonstrating persistence and recovery
public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        String filePath = "inventory.txt";
        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Attempt to load existing inventory from file
        persistenceService.loadInventory(inventory, filePath);

        // If inventory is empty, initialize default values
        if (inventory.getAllRooms().isEmpty()) {
            inventory.addRoomType("Single", 5);
            inventory.addRoomType("Double", 3);
            System.out.println("Initialized default inventory.");
        }

        inventory.showInventory();

        // Simulate booking operations
        System.out.println("\nSimulating bookings...");
        inventory.setRoomCount("Single", inventory.getRoomCount("Single") - 1); // Book one single room
        inventory.setRoomCount("Double", inventory.getRoomCount("Double") - 1); // Book one double room

        inventory.showInventory();

        // Persist updated inventory
        persistenceService.saveInventory(inventory, filePath);
    }
}