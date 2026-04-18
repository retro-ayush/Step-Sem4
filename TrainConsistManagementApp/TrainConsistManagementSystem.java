import java.util.ArrayList;

class PassengerBogie {
    private String id;
    private String type;
    private int capacity;

    public PassengerBogie(String id, String type, int capacity) {
        this.id = id;
        this.type = type;
        this.capacity = capacity;
    }

    public String toString() {
        return "Bogie ID: " + id + ", Type: " + type + ", Capacity: " + capacity;
    }
}

public class TrainConsistManagementSystem {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    Train Consist Management App   ");
        System.out.println("==================================");

        ArrayList<PassengerBogie> passengerBogies = new ArrayList<>();

        passengerBogies.add(new PassengerBogie("PB101", "Sleeper", 72));
        passengerBogies.add(new PassengerBogie("PB102", "AC Chair", 60));
        passengerBogies.add(new PassengerBogie("PB103", "First Class", 40));

        System.out.println("Passenger bogies added successfully.\n");

        System.out.println("Passenger Bogie List:");
        for (PassengerBogie bogie : passengerBogies) {
            System.out.println(bogie);
        }
    }
}