import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
    private final int port;
    private final String filePath;

    public FileServer(int port, String filePath) {
        this.port = port;
        this.filePath = filePath;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер амжилттай аслаа port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(new ClientHandler(clientSocket, filePath));
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileServer fileServer = new FileServer(1234, "C:\\Users\\Newtech\\IdeaProjects\\untitled");
        fileServer.start();
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final String filePath;

    public ClientHandler(Socket clientSocket, String filePath) {
        this.clientSocket = clientSocket;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try (
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream()
        ) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String fileName = reader.readLine();

            File file = new File(filePath, fileName);

            if (!file.exists() || !file.isFile()) {

                PrintWriter writer = new PrintWriter(out, true);
                writer.println("File ОЛДСОНГҮЙДЭЭ ХӨӨ");
                return;
            }

            try (FileInputStream fileIn = new FileInputStream(file)) {
                byte[] buffer = new byte[1000];
                int bytesRead;

                while ((bytesRead = fileIn.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                    out.flush();

                    Thread.sleep(200);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
