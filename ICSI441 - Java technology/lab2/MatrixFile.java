import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixFile {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        String outputOriginalFileName = "anhnii_matrits.txt";
        String outputConvertedFileName = "huvirgasan_matrits.txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            BufferedWriter originalWriter = new BufferedWriter(new FileWriter(outputOriginalFileName));
            BufferedWriter convertedWriter = new BufferedWriter(new FileWriter(outputConvertedFileName));

            String line;
            List<List<Integer>> currentMatrix = new ArrayList<>();
            int currentMatrixSum = 0;

            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    // diagonalin niilberig oli
                    int diagonalSum = 0;
                    for (int i = 0; i < currentMatrix.size(); i++) {
                        diagonalSum += currentMatrix.get(i).get(i);
                    }

                    if (diagonalSum % 2 == 0) {

                        writeMatrix(originalWriter, currentMatrix);
                    } else {
                        writeMatrix(convertedWriter, currentMatrix);
                    }

                    currentMatrix.clear();
                } else {
                    String[] values = line.split("\\s+");
                    List<Integer> row = new ArrayList<>();
                    for (String value : values) {
                        row.add(Integer.parseInt(value));
                    }
                    currentMatrix.add(row);
                }
            }

            originalWriter.close();
            convertedWriter.close();

            printFileContents(outputOriginalFileName);
            printFileContents(outputConvertedFileName);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMatrix(BufferedWriter writer, List<List<Integer>> matrix) throws IOException {
        for (List<Integer> row : matrix) {
            for (Integer value : row) {
                writer.write(value + " ");
            }
            writer.write("\n");
        }
        writer.write("\n");
    }

    private static void printFileContents(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println(fileName + ":");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
