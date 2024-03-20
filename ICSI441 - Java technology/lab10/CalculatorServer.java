import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    public static void main(String[] args) {
        final int PORT = 7007;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер амжилттай аслаа port: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {

                int operand1 = Integer.parseInt(reader.readLine());
                int operand2 = Integer.parseInt(reader.readLine());
                char operation = reader.readLine().charAt(0);

                int result;
                switch (operation) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            writer.println("ТЭГТ ХУВААГДАХГҮЙ ШҮҮ ДЭЭ!!!");
                            return;
                        }
                        break;
                    case '%':
                        result = operand1 % operand2;
                        break;
                    default:
                        writer.println("ТЭМДЭГЭЭ ДАХИН ШАЛГАНА УУ");
                        return;
                }

                writer.println("ХАРИУ: " + result);

            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
