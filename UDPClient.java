import java.io.*; 
import java.net.*;
import java.nio.file.Path;
import java.util.stream.Collectors;

class UDPClient {
   public static void main(String args[]) throws Exception
   {
      if (args.length < 2) {
         System.out.println("Usage: java UDPClient <server_ip> <port>");
         return;
      }

      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);

      Path path = Path.of("arquivo1.txt");
      File file = path.toFile();
      InputStream targetStream = new FileInputStream(file);

      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(targetStream));

      DatagramSocket clientSocket = new DatagramSocket();

      InetAddress IPAddress = InetAddress.getByName(serverAddr);

      byte[] sendData = new byte[1024];

      for(String string : inFromUser.lines().collect(Collectors.toList())) {
         System.out.println(string);
         String sentence = string;
         sendData = sentence.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         clientSocket.send(sendPacket);
      }      
      
      clientSocket.close();
   }
}
