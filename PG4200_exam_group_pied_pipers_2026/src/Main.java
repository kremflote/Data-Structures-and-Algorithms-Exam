import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        /* Import data and save as records in an ArrayList */
        String whitePath = "resources/winequality-white.csv";
        String redPath = "resources/winequality-red.csv";

        ArrayList<Wine> wines = DataHandler.loadAllWines(redPath, whitePath);

        printWineStats(wines);
    }

    private static void printWineStats(ArrayList<Wine> wines) {
        System.out.println("Total wines loaded: " + wines.size());
        System.out.println("\nFirst wine: " + wines.getFirst());
        System.out.println("Last wine:  " + wines.getLast());
    }
}