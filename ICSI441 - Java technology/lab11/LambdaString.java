import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface StringChecker {
    boolean check(String str);
}

public class LambdaString {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("java", "Geriin Daalgavar", "arvan","Neg", "lambda");

        StringChecker lowercaseChecker = str -> str.matches("^[a-z]+$");

        long lowercaseCount = stringList.stream()
                .flatMapToInt(String::chars)
                .filter(Character::isLowerCase)
                .count();

        System.out.println("Жижиг үсгүүдийн тоо: " + lowercaseCount);

        List<String> lowercaseStrings = stringList.stream()
                .filter(lowercaseChecker::check)
                .toList();

        System.out.println("Жижиг үсгээр бүх үгүүд: " + lowercaseStrings);
    }
}
