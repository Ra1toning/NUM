import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public class Chessboard {

    public static void main(String[] args) {
        try {
            String inputFilePath = "C:\\Users\\109\\IdeaProjects\\midterm2\\src\\input.txt";
            String outputFilePath = "C:\\Users\\109\\IdeaProjects\\midterm2\\src\\output.txt";

            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            String cellCoordinate = lines.get(0).trim();
            String color = determineCellColor.apply(cellCoordinate);

            Files.write(Paths.get(outputFilePath), color.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Function<String, String> determineCellColor = (coordinates) -> {
        char letter = coordinates.charAt(0);
        int digit = Integer.parseInt(coordinates.substring(1));

        boolean isBlack = (letter - 'A' + digit) % 2 == 0;

        return isBlack ? "WHITE" : "BLACK";
    };
}
