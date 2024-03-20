import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 7007;

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Эхний тоо: ");
            int operand1 = scanner.nextInt();
            System.out.print("Хоёр дахь тоо: ");
            int operand2 = scanner.nextInt();
            System.out.print("(+, -, *, /, %): ");
            char operation = scanner.next().charAt(0);


            writer.println(operand1);
            writer.println(operand2);
            writer.println(operation);

            String result = reader.readLine();
            System.out.println("СЕРВЕРЭЭС ИРСЭН - " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
