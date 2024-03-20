import java.io.*;
import java.text.*;
import java.util.Date;

public class BookTest {
    public static void main(String[] args) {
        Book[] books = new Book[5];

        try (BufferedReader reader = new BufferedReader(new FileReader("src/books.txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 5) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(parts[2].trim());
                    String publishingHouse = parts[3].trim();
                    double price = Double.parseDouble(parts[4].trim());
                    int copiesSold = Integer.parseInt(parts[5].trim());

                    books[index] = new Book(title, author, date, publishingHouse, price, copiesSold);
                    index++;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        System.out.println("'M' usgeer ehelsen zohiolchiin nomiin medeelel:");
        for (Book book : books) {
            if (book != null && book.getAuthor().startsWith("M")) {
                System.out.println(book);
            }
        }

        String mostPublishedHouse = findMostPublishedHouse(books);
        System.out.println("\nHamgiin olon nom hevlesen hevleliin gazar bol: " + mostPublishedHouse);
    }

    private static String findMostPublishedHouse(Book[] books) {
        java.util.HashMap<String, Integer> publishingHouseCounts = new java.util.HashMap<>();

        for (Book book : books) {
            String publishingHouse = book.getPublishingHouse();
            publishingHouseCounts.put(publishingHouse, publishingHouseCounts.getOrDefault(publishingHouse, 0) + 1);
        }

        int maxCount = 0;
        String mostPublishedHouse = null;
        for (String publishingHouse : publishingHouseCounts.keySet()) {
            int count = publishingHouseCounts.get(publishingHouse);
            if (count > maxCount) {
                maxCount = count;
                mostPublishedHouse = publishingHouse;
            }
        }

        return mostPublishedHouse;
    }
}
