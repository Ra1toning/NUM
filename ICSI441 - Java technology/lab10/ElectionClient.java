import java.io.*;
import java.net.*;

public class ElectionClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("СОНГУУЛИЙН НАСНЫ ХЭДЭН ИРГЭН БАЙГАА ВЭ? (N): ");
            int numVoters = Integer.parseInt(userInput.readLine());

            for (int i = 0; i < numVoters; i++) {
                System.out.println("СОНГУУЛИА ӨГНӨ ҮҮ! (A ЭСВЭЛ B): ");
                char vote = userInput.readLine().charAt(0);
                out.println(vote);
            }

            out.println("RESULT");

            String result = in.readLine();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
