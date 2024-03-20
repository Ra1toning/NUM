import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCompressor {

    @FunctionalInterface
    interface StringProcessor extends Function<String, String> {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Temdeg muruu oruulaach: ");
        String originalString = scanner.nextLine();

        System.out.println("Oruulsan temdegt mur: " + originalString);

        String compressedString = processString(originalString, s -> compress(s));
        System.out.println("davtagdsan toogoor solison String: " + compressedString);

        scanner.close();
    }

    private static String processString(String input, StringProcessor processor) {
        return processor.apply(input);
    }

    private static String compress(String input) {
        return input.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(Collectors.joining());
    }
}
