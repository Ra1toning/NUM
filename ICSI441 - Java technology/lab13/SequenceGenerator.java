import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("n toog oruul: ");
        int n = scanner.nextInt();

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        a.add(2);
        a.add(3);
        a.add(4);
        a.add(7);

        b.add(1);
        b.add(5);
        b.add(6);
        b.add(8);

        int i = 4;
        while (a.size() <= n || b.size() <= n) {
            a.add(b.get(i - 1) + b.get(i - 3));
            ++i;

            if (b.get(b.size() - 1) + 1 < a.get(a.size() - 1)) {
                int finalI = i;
                List<Integer> newValues = IntStream.range(b.get(b.size() - 1) + 1, a.get(a.size() - 1))
                        .filter(j -> j != a.get(finalI - 2))
                        .boxed()
                        .collect(Collectors.toList());
                b.addAll(newValues);
            }
        }

        System.out.println("hariu:");
        System.out.println(a.get(n - 1));
        System.out.println(b.get(n - 1));
    }
}
