import java.util.ArrayList;
import java.util.List;

class Wagon {
    private String id;
    private String type;

    public Wagon(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String toString() {
        return "Wagon ID: " + id + ", Type: " + type;
    }
}

public class TrainConsistManagementSystem {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    Train Consist Management App   ");
        System.out.println("==================================");

        List<Wagon> trainConsist = new ArrayList<>();

        trainConsist.add(new Wagon("P001", "Passenger - Sleeper"));
        trainConsist.add(new Wagon("P002", "Passenger - AC Chair"));
        trainConsist.add(new Wagon("G001", "Goods - Rectangular"));
        trainConsist.add(new Wagon("G002", "Goods - Cylindrical"));

        System.out.println("Train initialized successfully.\n");

        System.out.println("Train Consist Summary:");
        for (Wagon wagon : trainConsist) {
            System.out.println(wagon);
        }
    }
}