import java.util.Scanner;
import java.util.stream.IntStream;

public class TreasureMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int N = scanner.nextInt();

            double[] result = IntStream.range(0, N)
                    .mapToObj(i -> new int[]{scanner.nextInt(), scanner.nextInt()})
                    .map(entry -> {
                        double angle = (entry[0] - 1) * Math.PI / 4;
                        return new double[]{entry[1] * Math.cos(angle), entry[1] * Math.sin(angle)};
                    })
                    .collect(() -> new double[]{0, 0}, (a, b) -> {
                        a[0] += b[0];
                        a[1] += b[1];
                    }, (a, b) -> {
                        a[0] += b[0];
                        a[1] += b[1];
                    });

            System.out.printf("%.3f %.3f\n", result[1], result[0]);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format!");
        }
    }
}
