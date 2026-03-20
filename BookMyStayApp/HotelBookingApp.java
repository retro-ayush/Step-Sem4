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
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("=== Add-On Service चयन (Selection) ===");

        // Assume these are confirmed reservation IDs
        String res1 = "S101";
        String res2 = "D201";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa", 1500);
        AddOnService pickup = new AddOnService("Airport Pickup", 800);

        // Service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services to reservations
        manager.addService(res1, breakfast);
        manager.addService(res1, spa);

        manager.addService(res2, pickup);

        // Display total cost
        System.out.println("Total Add-On Cost for " + res1 + ": ₹" +
                manager.calculateTotalServiceCost(res1));

        System.out.println("Total Add-On Cost for " + res2 + ": ₹" +
                manager.calculateTotalServiceCost(res2));
    }
}

// CLASS - AddOnService
class AddOnService {

    private String serviceName;
    private double cost;

    // Constructor (FIXED: name must match class)
    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

// CLASS - AddOnServiceManager
class AddOnServiceManager {

    // Map: Reservation ID -> List of services
    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getServiceName() +
                " added to Reservation " + reservationId);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) {
            return 0;
        }

        double total = 0;

        for (AddOnService service : services) {
            total += service.getCost();
        }

        return total;
    }
}