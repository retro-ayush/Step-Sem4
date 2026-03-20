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
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("=== Booking History & Report ===");

        // Create booking history
        BookingHistory history = new BookingHistory();

        // Simulate confirmed reservations
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));
        history.addReservation(new Reservation("Kumar", "Single"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}

// CLASS - BookingHistory
class BookingHistory {

    // Stores confirmed reservations
    private List<Reservation> confirmedReservations;

    // Constructor
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add reservation
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    // Get all reservations
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

// CLASS - BookingReportService
class BookingReportService {

    // Generate report
    public void generateReport(BookingHistory history) {

        List<Reservation> reservations = history.getConfirmedReservations();

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n--- Booking Report ---");

        Map<String, Integer> countByType = new HashMap<>();

        // Display each booking
        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName() +
                    " | Room Type: " + r.getRoomType());

            // Count room types
            countByType.put(
                    r.getRoomType(),
                    countByType.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        // Summary
        System.out.println("\n--- Summary ---");
        for (String type : countByType.keySet()) {
            System.out.println(type + " Rooms Booked: " + countByType.get(type));
        }

        System.out.println("Total Bookings: " + reservations.size());
    }
}

// CLASS - Reservation (reuse from previous use cases)
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