import java.util.LinkedHashSet;

public class TrainConsistManagementSystem {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    Train Consist Management App   ");
        System.out.println("==================================");

        LinkedHashSet<String> bogieIds = new LinkedHashSet<>();

        bogieIds.add("PB101");
        bogieIds.add("PB102");
        bogieIds.add("GB201");
        bogieIds.add("PB103");
        bogieIds.add("GB202");
        bogieIds.add("PB101");

        System.out.println("Bogie IDs in insertion order:");
        for (String id : bogieIds) {
            System.out.println(id);
        }
    }
}