import java.io.*;

class ArrayProcessor implements Runnable {
    private int[] array;
    private String fileName;

    public ArrayProcessor(int[] array, String fileName) {
        this.array = array;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(array);
            objectOutputStream.close();

            System.out.println(fileName + ": Min = " + min + ", Max = " + max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ArrayTest {
    public static void main(String[] args) {
        int[] array1 = {4, 8, 1, 9};
        int[] array2 = {5, 7, 3, 2};

        Thread thread1 = new Thread(new ArrayProcessor(array1, "array1.bin"));
        Thread thread2 = new Thread(new ArrayProcessor(array2, "array2.bin"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] readArray1;
        int[] readArray2;

        try {
            FileInputStream fileInputStream1 = new FileInputStream("array1.bin");
            ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);
            readArray1 = (int[]) objectInputStream1.readObject();
            objectInputStream1.close();

            FileInputStream fileInputStream2 = new FileInputStream("array2.bin");
            ObjectInputStream objectInputStream2 = new ObjectInputStream(fileInputStream2);
            readArray2 = (int[]) objectInputStream2.readObject();
            objectInputStream2.close();

            int sumArray1 = 0;
            int sumArray2 = 0;

            for (int num : readArray1) {
                sumArray1 += num;
            }

            for (int num : readArray2) {
                sumArray2 += num;
            }

            System.out.println("Array1-Н НИЙЛБЭР: " + sumArray1);
            System.out.println("Array2-Н НИЙЛБЭР: " + sumArray2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
