import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class PassengerAnalyzer {

    public static void main(String[] args) throws IOException {
        List<Passenger> passengers = Files.lines(Path.of("C:\\Users\\Newtech\\IdeaProjects\\untitled\\PassengerTest.txt"))
                .map(line -> {
                    String[] parts = line.split(",");
                    int numberOfItems = Integer.parseInt(parts[0]);
                    double totalWeight = Double.parseDouble(parts[1]);
                    return new Passenger(numberOfItems, totalWeight);
                })
                .collect(Collectors.toList());

        // a) dundjaas zuruu ni 0.3kg-s hetrehgui juntei teesh olno
        double averageWeight = passengers.stream()
                .mapToDouble(Passenger::getTotalWeight)
                .average()
                .orElse(0.0);

        List<Passenger> luggageWithinRange = passengers.stream()
                .filter(passenger -> Math.abs(passenger.getTotalWeight() - averageWeight) <= 0.3)
                .collect(Collectors.toList());

        System.out.println("a) Dundjaas 0.3kg-s hetrehgui jintei teesh:");
        luggageWithinRange.forEach(passenger -> System.out.println("Shirheg: " + passenger.getNumberOfItems() + ", Jin: " + passenger.getTotalWeight()));

        // b) Hoyroos iluu teeshtei zorchigchiin toog olno
        long passengersWithMoreThanTwoItems = passengers.stream()
                .filter(passenger -> passenger.getNumberOfItems() > 2)
                .count();

        // dundjaas olon teeshtei zorchigchiin toog olno
        double averageNumberOfItems = passengers.stream()
                .mapToInt(Passenger::getNumberOfItems)
                .average()
                .orElse(0.0);

        long passengersWithMoreItemsThanAverage = passengers.stream()
                .filter(passenger -> passenger.getNumberOfItems() > averageNumberOfItems)
                .count();

        System.out.println("\nb)Hoyroos olon baraatai zorchigciin too: " + passengersWithMoreThanTwoItems);
        System.out.println("Niit baraanii dundjaas olon baraatai zorchigciin too: " + passengersWithMoreItemsThanAverage);

        // c) Ijil toonii baraatai jingiin zuruu ni 0.5kg hetrehgui iim hoyr zorchigch baigaa eseh
        boolean hasSimilarPassengers = passengers.stream()
                .anyMatch(passenger1 ->
                        passengers.stream()
                                .filter(passenger2 -> !passenger1.equals(passenger2))
                                .anyMatch(passenger2 ->
                                        passenger1.getNumberOfItems() == passenger2.getNumberOfItems()
                                                && Math.abs(passenger1.getTotalWeight() - passenger2.getTotalWeight()) <= 0.5
                                )
                );

        System.out.println("\nc) Oiroltsoo baraatai zorchigch baigaa eseh: " + hasSimilarPassengers);

        // d) busad zorchigchdoos baraanii too jingeer ylgaatai zorchigch baigaa eseh
        boolean differInItemsAndWeight = passengers.stream()
                .anyMatch(passenger1 ->
                        passengers.stream()
                                .filter(passenger2 -> !passenger1.equals(passenger2))
                                .anyMatch(passenger2 ->
                                        passenger1.getNumberOfItems() != passenger2.getNumberOfItems()
                                                || Math.abs(passenger1.getTotalWeight() - passenger2.getTotalWeight()) > 0.5
                                )
                );

        System.out.println("\nd) busad zorchigchoos baraanii too jingeer ylgaatai zorchigch baigaa eseh: " + differInItemsAndWeight);

        // e) 30kg aas hetrehgui gantshan baraatai zorchigch
        boolean hasPassengerWithOneItemAndWeightLessThan30 = passengers.stream()
                .anyMatch(passenger -> passenger.getNumberOfItems() == 1 && passenger.getTotalWeight() <= 30);

        System.out.println("\ne) 30kg aas hetrehgui gantshan baraatai zorchigch: " + hasPassengerWithOneItemAndWeightLessThan30);
    }
}

class Passenger {
    private final int numberOfItems;
    private final double totalWeight;

    public Passenger(int numberOfItems, double totalWeight) {
        this.numberOfItems = numberOfItems;
        this.totalWeight = totalWeight;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public double getTotalWeight() {
        return totalWeight;
    }
}
