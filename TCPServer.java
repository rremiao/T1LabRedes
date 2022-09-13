import java.io.*;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
 
// Recebe uma mensagem de algum cliente
// Imprime mensagem na tela

public class TCPServer {
 
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java TCPServer <port>");
            return;
        }
 
        int port = Integer.parseInt(args[0]);
        Path file = Paths.get("testeArquivo1.txt");


        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
            
            while (true) {
                Socket socket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = new FileOutputStream(file.toFile());
                PrintWriter writer = new PrintWriter(out, true);

                System.out.println("New client connected: " + socket.getRemoteSocketAddress()); 
                byte[] bytes = new byte[16*1024];

                int count;
                while ((count = in.read(Arrays.toString(bytes).toCharArray())) >= 0) {
                    System.out.println("teste server");
                    writer.print(bytes);
                }
                System.out.println("teste close");

                break;
            }            
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getStackTrace());
            ex.printStackTrace();
        }
    }
}
