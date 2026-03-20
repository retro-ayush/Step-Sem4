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

// MAIN CLASS (only public class)
public class UseCase6RoomAllocation {

    public static void main(String[] args) {

        System.out.println("=== Room Allocation System ===");

        // Inventory setup
        RoomInventory inventory = new RoomInventory();

        // Booking queue (FIFO)
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Double"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));
        queue.addRequest(new Reservation("Kumar", "Single"));

        // Allocation service
        RoomAllocationService allocationService = new RoomAllocationService();

        // Process queue
        while (queue.hasPendingRequests()) {
            Reservation request = queue.getNextRequest();
            allocationService.allocateRoom(request, inventory);
            System.out.println("--------------------------------");
        }
    }
}

// CLASS - RoomAllocationService
class RoomAllocationService {

    // Track all allocated room IDs (no duplicates)
    private Set<String> allocatedRoomIds;

    // Track assigned rooms by type
    private Map<String, Set<String>> assignedRoomsByType;

    // Constructor
    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    // Allocate room
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check availability
        if (availability.getOrDefault(roomType, 0) <= 0) {
            System.out.println("No " + roomType + " rooms available for " + reservation.getGuestName());
            return;
        }

        // Generate unique room ID
        String roomId = generateRoomId(roomType);

        // Store allocation
        allocatedRoomIds.add(roomId);

        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        // Update inventory (decrement)
        availability.put(roomType, availability.get(roomType) - 1);

        // Confirmation output
        System.out.println("Booking Confirmed!");
        System.out.println("Guest: " + reservation.getGuestName());
        System.out.println("Room Type: " + roomType);
        System.out.println("Allocated Room ID: " + roomId);
    }

    // Generate unique room ID
    private String generateRoomId(String roomType) {

        String prefix = roomType.substring(0, 1).toUpperCase();
        String roomId;

        do {
            int number = (int) (Math.random() * 900 + 100); // 3-digit number
            roomId = prefix + number;
        } while (allocatedRoomIds.contains(roomId));

        return roomId;
    }
}

// CLASS - Reservation
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// CLASS - BookingRequestQueue (FIFO)
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

// CLASS - RoomInventory
class RoomInventory {

    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("Single", 2);
        roomAvailability.put("Double", 1);
        roomAvailability.put("Suite", 1);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }
}