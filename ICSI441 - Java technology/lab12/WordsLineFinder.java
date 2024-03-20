import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;

public class WordsLineFinder {
    public static void main(String[] args) {

        Path filePath = Path.of("C:\\Users\\Newtech\\IdeaProjects\\untitled\\src\\content.txt");

        try {

            String lineWithMostWords = Files.lines(filePath)
                    .max(Comparator.comparingInt(WordsLineFinder::countWords))
                    .orElse("tiim file alga");

            System.out.println("hamgiin olon ugtei mur: " + lineWithMostWords);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int countWords(String line) {
        return (int) Arrays.stream(line.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .count();
    }
}
