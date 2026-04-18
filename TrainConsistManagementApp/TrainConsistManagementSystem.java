import java.util.SortedSet;
import java.util.TreeSet;

public class TrainConsistManagementSystem {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("    Train Consist Management App   ");
        System.out.println("==================================");

        SortedSet<String> bogieIds = new TreeSet<>();

        bogieIds.add("PB103");
        bogieIds.add("PB101");
        bogieIds.add("GB201");
        bogieIds.add("PB102");
        bogieIds.add("GB202");
        bogieIds.add("PB101");

        System.out.println("Ordered Unique Bogie IDs:");
        for (String id : bogieIds) {
            System.out.println(id);
        }
    }
}