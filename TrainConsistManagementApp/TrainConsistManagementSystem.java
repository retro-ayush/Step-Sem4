import java.util.HashSet;

public class TrainConsistManagementSystem {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    Train Consist Management App   ");
        System.out.println("==================================");

        HashSet<String> bogieIds = new HashSet<>();

        bogieIds.add("PB101");
        bogieIds.add("PB102");
        bogieIds.add("PB103");
        bogieIds.add("PB101");
        bogieIds.add("PB104");

        System.out.println("Unique Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }
    }
}