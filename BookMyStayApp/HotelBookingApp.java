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

// Simple Reservation class
class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Thread-safe booking queue
class BookingRequestQueue {
    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addReservation(Reservation reservation) {
        synchronized (queue) {
            queue.add(reservation);
            queue.notify(); // wake up waiting threads
        }
    }

    public Reservation getNextReservation() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait(); // wait until a reservation is added
            }
            return queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}

// RoomInventory class
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Allocate a room if available
    public boolean allocateRoom(String roomType) {
        synchronized (this) {
            int available = inventory.getOrDefault(roomType, 0);
            if (available > 0) {
                inventory.put(roomType, available - 1);
                return true;
            }
            return false;
        }
    }

    public void showInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + ", Available: " + entry.getValue());
        }
    }
}

// RoomAllocationService class
class RoomAllocationService {
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        boolean success = inventory.allocateRoom(reservation.roomType);
        if (success) {
            System.out.println("Reservation " + reservation.reservationId +
                    " allocated room type " + reservation.roomType);
        } else {
            System.out.println("Reservation " + reservation.reservationId +
                    " failed to allocate room type " + reservation.roomType + " (sold out)");
        }
    }
}

// ConcurrentBookingProcessor handles booking requests in threads
public class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (true) {
            Reservation reservation = null;
            // Retrieve reservation safely
            try {
                reservation = bookingQueue.getNextReservation();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted while waiting for reservations.");
                break;
            }

            // Allocate room safely
            allocationService.allocateRoom(reservation, inventory);

            // Exit condition: stop if queue is empty
            synchronized (bookingQueue) {
                if (bookingQueue.isEmpty()) break;
            }
        }
    }
}

// Main class to demonstrate concurrent booking
public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Double", 1);

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addReservation(new Reservation("R101", "Single"));
        bookingQueue.addReservation(new Reservation("R102", "Double"));
        bookingQueue.addReservation(new Reservation("R103", "Single"));
        bookingQueue.addReservation(new Reservation("R104", "Single"));

        // Create booking processor tasks
        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Show final inventory
        inventory.showInventory();
    }
}