import java.io.*;
import java.net.Socket;

public class FileClient {
    private final String serverAddress;
    private final int serverPort;
    private final String downloadPath;

    public FileClient(String serverAddress, int serverPort, String downloadPath) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.downloadPath = downloadPath;
    }

    public void downloadFile(String fileName) {
        try (Socket socket = new Socket(serverAddress, serverPort);
             InputStream in = socket.getInputStream();
             OutputStream out = new FileOutputStream(downloadPath + File.separator + fileName);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println(fileName);

            byte[] buffer = new byte[1000];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("Тухайн File Энэ замд хадгалагдлаа: " + downloadPath + File.separator + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String downloadPath = "C:\\Users\\Newtech\\IdeaProjects\\untitled\\downloaded_files";
        FileClient fileClient = new FileClient("localhost", 1234, downloadPath);
        fileClient.downloadFile("test.txt");
    }
}
