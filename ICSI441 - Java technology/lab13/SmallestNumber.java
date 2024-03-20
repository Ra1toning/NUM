import java.util.Scanner;

@FunctionalInterface
interface NumberFinder {
    int findNumber(int N);
}

public class SmallestNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("N toog oruul: ");
        int N = scanner.nextInt();

        NumberFinder numberFinder = SmallestNumber::findSmallestNumber;

        int result = numberFinder.findNumber(N);

        System.out.println("hamgiin baga natural Q too: " + result);

        scanner.close();
    }

    private static int findSmallestNumber(int N) {
        if (N == 0) {
            return 10;
        }

        StringBuilder resultDigits = new StringBuilder();

        for (int i = 9; i >= 2; i--) {
            while (N % i == 0) {
                resultDigits.append(i);
                N /= i;
            }
        }

        if (N > 1) {
            return -1;
        }

        return Integer.parseInt(resultDigits.reverse().toString());
    }
}
