import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.io.*;
 
// Le uma linha do teclado
// Envia a linha digitada ao servidor

public class TCPClient {
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java TCPClient <server_ip> <port>");
            return;
        }
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try (Socket socket = new Socket(hostname, port)) {
 
            Path path = Path.of("Lorem.txt");
            
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            for(String string : Files.lines(path).collect(Collectors.toList())) {
                System.out.println(string);
                writer.println(string);
            }
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
