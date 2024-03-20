import java.util.Arrays;
import java.util.Comparator;

public class StringSorting {
    public static void main(String[] args) {
        String[] array1 = {"hello", "spoon", "cat"};
        String[] array2 = {"I", "love", "to", "music"};
        String[] array3 = {"world", "job", "july"};

        String[][] arrays = {array1, array2, array3};

        Comparator<String[]> byWordCount = Comparator.comparingInt(arr -> Arrays.stream(arr)
                .mapToInt(s -> s.split("\\s+").length)
                .sum());


        Comparator<String[]> byTotalLetters = Comparator.comparingInt(arr -> Arrays.stream(arr)
                .mapToInt(String::length)
                .sum());

        Arrays.sort(arrays, byWordCount);

        System.out.println("Үгээр нь эрэмбэлсэн байдал:");
        Arrays.stream(arrays).map(Arrays::toString).forEach(System.out::println);

        Arrays.sort(arrays, byTotalLetters);

        System.out.println("\nҮсгээр нь эрэмбэлсэн байдал:");
        Arrays.stream(arrays).forEach(arr -> {
            int totalLetters = Arrays.stream(arr).mapToInt(String::length).sum();
            System.out.println( totalLetters + " үсэг");
        });
    }
}
