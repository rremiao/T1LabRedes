import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 
        Path file = Paths.get("testeArquivo1.txt");
        byte [] fileArray;

        try (Socket socket = new Socket(hostname, port)) {
 
            while(true) {

                fileArray = Files.readAllBytes(file);
                InputStream in = new FileInputStream(file.toFile());
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);           
                int count;

                while((count = in.read(fileArray)) > 0) {
                    System.out.println("teste");
                    writer.print(fileArray);
                }

                //output.close();
                //in.close();      
                //socket.close();           
            }
            
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
