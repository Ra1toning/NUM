import java.io.*;
import java.util.*;

class DairyProduct implements Serializable {
    private String name;
    private int year, month, day;

    private int storageInDays;

    public DairyProduct(String name, int year, int month, int day, int storageInDays) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.storageInDays = storageInDays;
    }

    public boolean isExpired(int currentYear, int currentMonth, int currentDay) {
        int expirationYear = year;
        int expirationMonth = month;
        int expirationDay = day + storageInDays;

        if (expirationDay > 31) {
            expirationMonth += expirationDay / 31;
            expirationDay %= 31;
        }
        if (expirationMonth > 12) {
            expirationYear += expirationMonth / 12;
            expirationMonth %= 12;
        }

        if (currentYear > expirationYear || (currentYear == expirationYear && currentMonth > expirationMonth) ||
                (currentYear == expirationYear && currentMonth == expirationMonth && currentDay > expirationDay)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return name + ", Дуусах хугацаа: " + year + "-" + month + "-" + (day + storageInDays);
    }
}

public class DairyProductManager {
    public static void main(String[] args) {
        List<DairyProduct> products = new ArrayList<>();

        int currentYear = 2023;
        int currentMonth = 10;
        int currentDay = 11;

        products.add(new DairyProduct("Сүү", 2023, 1, 1, 7));
        products.add(new DairyProduct("Тараг", 2023, 2, 15, 10));
        products.add(new DairyProduct("Бяслаг", 2023, 12, 10, 30));

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("dairy_products.dat"))) {
            outputStream.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("dairy_products.dat"))) {
            List<DairyProduct> savedProducts = (List<DairyProduct>) inputStream.readObject();

            for (DairyProduct product : savedProducts) {
                if (product.isExpired(currentYear, currentMonth, currentDay)) {
                    System.out.println("Хугацаа нь дууссан: " + product);
                } else {
                    System.out.println("Хугацаа нь дуусаагүй: " + product);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
