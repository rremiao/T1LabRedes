import java.io.*;
import java.net.*;
import java.util.stream.Collectors;
 
// Recebe uma mensagem de algum cliente
// Imprime mensagem na tela

public class TCPServer {
 
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java TCPServer <port>");
            return;
        }
 
        int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected: " + socket.getRemoteSocketAddress()); 
 
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                for(String line: in.lines().collect(Collectors.toList())) {
                    System.out.println("Mensagem recebida: " + line);
                }               
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
