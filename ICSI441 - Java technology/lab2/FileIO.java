import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static void main(String[] args) {
        try {
            // fileuuda nerlechihne
            String inputFile = "f.txt";
            String negativeOutputFile = "h.txt";
            String mixedOutputFile = "g.txt";

            // inputees buh toogoo unshaad surug utgatai toogoo salgana
            List<Integer> negativeNumbers = new ArrayList<>();
            List<Integer> positiveNumbers = new ArrayList<>();

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number < 0) {
                    negativeNumbers.add(number);
                } else {
                    positiveNumbers.add(number);
                }
            }
            reader.close();

            // h file uusgeed terendee surug toogoo chihne
            BufferedWriter negativeWriter = new BufferedWriter(new FileWriter(negativeOutputFile));
            for (int num : negativeNumbers) {
                negativeWriter.write(num + "\n");
            }
            negativeWriter.close();

            // g file uusget tuundee 2 eyreg 2 surug geh baidlaar davtalt uusgenee
            BufferedWriter mixedWriter = new BufferedWriter(new FileWriter(mixedOutputFile));
            int pIndex = 0;
            int nIndex = 0;

            while (pIndex < positiveNumbers.size() && nIndex < negativeNumbers.size()) {
                for (int i = 0; i < 2 && pIndex < positiveNumbers.size(); i++) {
                    mixedWriter.write(positiveNumbers.get(pIndex) + "\n");
                    pIndex++;
                }
                for (int i = 0; i < 2 && nIndex < negativeNumbers.size(); i++) {
                    mixedWriter.write(negativeNumbers.get(nIndex) + "\n");
                    nIndex++;
                }
            }
            mixedWriter.close();

            System.out.println("Surug toog " + negativeOutputFile + "-d amjilttai hadgallaa");
            System.out.println("hoyr eyreg,hoyr surug too bolgon huvirgaj " + mixedOutputFile + " filed amjilttai hadgallaa");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
