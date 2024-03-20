import java.io.*;

public class BinaryFiles {

    public static void main(String[] args) throws IOException {
        String inputFile1 = "file1.bin";
        String inputFile2 = "file2.bin";
        String outputFile = "output.bin";

        FileInputStream fis1 = new FileInputStream(inputFile1);
        FileInputStream fis2 = new FileInputStream(inputFile2);
        FileOutputStream fos = new FileOutputStream(outputFile);


        byte[] buffer = new byte[5000];

        int b1 = fis1.read();
        int b2 = fis2.read();

        while (b1 != -1 && b2 != -1) {
            if (b1 < b2) {
                fos.write(b1);
                b1 = fis1.read();
            } else {
                fos.write(b2);
                b2 = fis2.read();
            }
        }

        if (b1 != -1) {
            fos.write(b1);
            while ((b1 = fis1.read()) != -1) {
                fos.write(b1);
            }
        } else if (b2 != -1) {
            fos.write(b2);
            while ((b2 = fis2.read()) != -1) {
                fos.write(b2);
            }
        }

        fis1.close();
        fis2.close();
        fos.close();
    }
}
