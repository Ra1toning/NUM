import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface StoreFunction {
    double calculateTotal(double price, int quantity, double discount);
}

class Store {
    static double suggestPurchase(String itemName, double itemPrice, int quantity, double discount, StoreFunction storeFunction) {
        System.out.println("Та " + quantity + " ширхэг " + itemName + "-г ширхэг тус бүрийг нь " + itemPrice + "₮-нөөс " + discount + "₮-р хямдруулж авж байна.");
        return storeFunction.calculateTotal(itemPrice, quantity, discount);
    }

    public static void main(String[] args) {

        List<Object[]> items = new ArrayList<>();
        items.add(new Object[]{"Талх", 2950.0, 3, 30.0});
        items.add(new Object[]{"Сүү", 2030.0, 2, 100.0});
        items.add(new Object[]{"Ундаа", 1950.0, 1, 0.0});
        items.add(new Object[]{"Чихэр", 5500.0, 4, 200.0});
        items.add(new Object[]{"Алим", 9800.0, 5, 400.0});

        double totalAmount = items.stream()
                .mapToDouble(item -> suggestPurchase((String) item[0], (double) item[1], (int) item[2], (double) item[3], (price, quantity, discount) -> price * quantity - discount))
                .sum();

        System.out.println("\nНийт үнэ: " + totalAmount + "₮ боллоо.");
    }
}
