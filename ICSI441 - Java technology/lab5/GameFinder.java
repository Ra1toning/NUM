import java.io.*;

public class GameFinder {
    public static void main(String[] args) {
        try {
            createDataFile("games.txt");

            // Даалгавар 1:
            double maxPrice = 30000; // k төгрөг
            String ageGroup = "5 нас"; // 5 настай хүүхдэд зориулсан
            findGamesFor5YearOlds("games.txt", ageGroup, maxPrice);

            // Даалгавар 2: Хамгийн үнэтэй эвлүүлдэг тоглоом
            findMostExpensivePuzzle("games.txt");

            // Даалгавар 3: 4-10 настай хүүхдүүдэд зориулсан тоглоомнууд
            String ageGroup1 = "4 нас";
            String ageGroup2 = "10 нас";
            findGames4and10YearsOld("games.txt", ageGroup1, ageGroup2);

            // Даалгавар 4: 3настай хүүхдэд зориулсан бөмбөгнөөс өөр тоглоом
            String ageGroup3 = "3 нас";
            String bumbug = "Бөмбөг";
            findToys3YearOld("games.txt", ageGroup3, bumbug);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDataFile(String games) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(games);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF("Модон морь");
            dos.writeDouble(10000);
            dos.writeUTF("2 нас");

            dos.writeUTF("Рубик шоо");
            dos.writeDouble(15450);
            dos.writeUTF("5 нас");

            dos.writeUTF("Бөмбөг");
            dos.writeDouble(49500);
            dos.writeUTF("3 нас");

            dos.writeUTF("Тоглоомон машин");
            dos.writeDouble(75000);
            dos.writeUTF("3 нас");

            dos.writeUTF("Эвлүүлдэг тоглоом");
            dos.writeDouble(9500);
            dos.writeUTF("4 нас");

            dos.writeUTF("Мортол");
            dos.writeDouble(5000);
            dos.writeUTF("10 нас");

            dos.writeUTF("Эвлүүлдэг тоглоом");
            dos.writeDouble(19580);
            dos.writeUTF("10 нас");
        }
    }

    private static void findGamesFor5YearOlds(String fileName, String ageGroup, double maxPrice) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                String name = dis.readUTF();
                double price = dis.readDouble();
                String age = dis.readUTF();

                if (age.equals(ageGroup) && price <= maxPrice) {
                    System.out.println("5 настай хүүхэд зориулсан тоглоом: " + name);
                }
            }
            System.out.println("\n");
        }
    }

    private static void findMostExpensivePuzzle(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            double maxPrice = Double.MIN_VALUE;
            String puzzleName = "";
            String puzzleAge = "";

            while (dis.available() > 0) {
                String name = dis.readUTF();
                double price = dis.readDouble();
                String age = dis.readUTF();

                if (name.equalsIgnoreCase("Эвлүүлдэг тоглоом") && price > maxPrice) {
                    maxPrice = price;
                    puzzleName = name;
                    puzzleAge = age;
                }
            }

            if (!puzzleName.isEmpty()) {
                System.out.println("Хамгийн үнэтэй эвлүүлдэг тоглоом:");
                System.out.println("Нэр: " + puzzleName);
                System.out.println("Үнэ: " + maxPrice);
                System.out.println("Насны ангилал: " + puzzleAge);
            } else {
                System.out.println("Тийм юм алга өө.");
            }
            System.out.println("\n");
        }
    }

    private static void findGames4and10YearsOld(String fileName, String ageGroup1, String ageGroup2) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                String name = dis.readUTF();
                double price = dis.readDouble();
                String age = dis.readUTF();

                if (age.equals(ageGroup1) || age.equals(ageGroup2) ){
                    System.out.println("4 ба 10 настай хүүхдэд тохирох тоглоом: " + name);
                }
            }
            System.out.println("\n");
        }
    }

    private static void findToys3YearOld(String fileName, String ageGroup, String bumbug) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            while (dis.available() > 0) {
                String name = dis.readUTF();
                double price = dis.readDouble();
                String age = dis.readUTF();

                if (age.equals(ageGroup) && !name.equalsIgnoreCase(bumbug)) {
                    System.out.println("Бөмбөгнөөс өөр 3 настай хүүхдэд тохирох тоглоом: " + name);
                }
            }
        }
    }
}
