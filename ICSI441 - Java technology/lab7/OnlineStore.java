import java.util.*;

public class OnlineStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Map<String, Integer>> buyers = new TreeMap<>();
        List<String> purchases = new ArrayList<>();

        String input = "Bat paper 10\n" +
                "Purev pens 5\n" +
                "Bat marker 3\n" +
                "Bat paper 7\n" +
                "Purev envelope 20\n" +
                "Bat envelope 5";
        scanner = new Scanner(input);

        while (scanner.hasNext()) {
            String buyer = scanner.next();
            String item = scanner.next();
            int quantity = scanner.nextInt();

            //даалгавар 1
            buyers.putIfAbsent(buyer, new HashMap<>());
            buyers.get(buyer).put(item, buyers.get(buyer).getOrDefault(item, 0) + quantity);

            // даалгавар 2.
            purchases.add(buyer + " " + item + " " + quantity);
        }
        //даалгавар 1
        for (String buyer : buyers.keySet()) {
            System.out.println(buyer + ":");
            Map<String, Integer> items = buyers.get(buyer);
            for (String item : items.keySet()) {
                System.out.println(item + " " + items.get(item));
            }
        }
        System.out.println("\n");
        // даалгавар2
        Collections.sort(purchases);
        for (String purchase : purchases) {
            System.out.println(purchase);
        }
    }
}
