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
import java.util.*;

// RoomInventory class to manage room counts by type
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Add room type with initial count
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Increment inventory for a room type
    public void restoreRoom(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    // Display current inventory
    public void showInventory() {
        System.out.println("Current Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + ", Available: " + entry.getValue());
        }
    }
}

// CancellationService handles booking cancellations
public class CancellationService {

    /** Stack that stores recently released room IDs. */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type. */
    private Map<String, String> reservationRoomTypeMap;

    /** Initializes cancellation tracking structures. */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking.
     *
     * @param reservationId confirmed reservation ID
     * @param roomType allocated room type
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
        System.out.println("Booking registered: " + reservationId + " -> " + roomType);
    }

    /**
     * Cancels a confirmed booking and restores inventory safely.
     *
     * @param reservationId reservation to cancel
     * @param inventory centralized room inventory
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid cancellation: Reservation ID " + reservationId + " not found.");
            return;
        }
        String roomType = reservationRoomTypeMap.remove(reservationId);
        inventory.restoreRoom(roomType);
        releasedRoomIds.push(reservationId);
        System.out.println("Booking cancelled: " + reservationId + " -> " + roomType);
    }

    /** Displays recently cancelled reservations. */
    public void showRollbackHistory() {
        System.out.println("Recently Cancelled Reservations (Rollback Order):");
        for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
            System.out.println(releasedRoomIds.get(i));
        }
    }
}

// Main class to demonstrate Use Case 10
public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);

        CancellationService cancellationService = new CancellationService();

        // Register bookings
        cancellationService.registerBooking("R001", "Single");
        cancellationService.registerBooking("R002", "Double");
        cancellationService.registerBooking("R003", "Single");

        inventory.showInventory();

        // Cancel bookings
        cancellationService.cancelBooking("R002", inventory);
        cancellationService.cancelBooking("R003", inventory);

        inventory.showInventory();

        // Show rollback history
        cancellationService.showRollbackHistory();
    }
}