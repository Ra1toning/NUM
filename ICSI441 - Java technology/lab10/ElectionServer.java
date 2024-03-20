import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ElectionServer {
    private static final int PORT = 8080;
    private static Map<Character, Integer> votes = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("СОНГУУЛИЙН СЕРВЕР АСЛАА PORT: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ElectionClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static synchronized void processVote(char candidate) {
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
    }

    static synchronized String announceWinner() {
        char winner = ' ';
        int maxVotes = 0;

        for (Map.Entry<Character, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }
        return "СОНГУУЛИЙН ДҮН: " + winner + " НЬ " + maxVotes + " САНАЛААР ЯЛЛАА!!!";
    }
}
