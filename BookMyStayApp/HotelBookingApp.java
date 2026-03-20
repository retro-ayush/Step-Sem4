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
import java.util.LinkedList;
import java.util.Queue;

// MAIN CLASS (only public class)
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Display application header
        System.out.println("=== Booking Request Queue (FIFO) ===");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        // Add requests to the queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process and display requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {
            Reservation current = bookingQueue.getNextRequest();

            System.out.println("Processing Booking Request:");
            System.out.println("Guest Name: " + current.getGuestName());
            System.out.println("Requested Room: " + current.getRoomType());
            System.out.println("---------------------------");
        }
    }
}

// CLASS - Reservation
class Reservation {

    // Name of the guest
    private String guestName;

    // Requested room type
    private String roomType;

    // Constructor
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    // Getters
    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// CLASS - BookingRequestQueue
class BookingRequestQueue {

    // Queue to store booking requests
    private Queue<Reservation> requestQueue;

    // Constructor
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add request (enqueue)
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    // Get next request (dequeue)
    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    // Check if queue has pending requests
    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}