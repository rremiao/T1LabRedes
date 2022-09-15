
// Le uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket
import java.nio.file.Path;
import java.util.stream.Collector;
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

      // cria o stream do teclado
      Path path = Path.of("Lorem.txt");
      File file = path.toFile();
      InputStream targetStream = new FileInputStream(file);

      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(targetStream));

      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket();

      // obtem endereco IP do servidor com o DNS
      InetAddress IPAddress = InetAddress.getByName(serverAddr);

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      // le uma linha do teclado
      for(String string : inFromUser.lines().collect(Collectors.toList())) {
         System.out.println(string);
         String sentence = string;
         sendData = sentence.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
         clientSocket.send(sendPacket);
      }      
      
      // cria pacote com o dado, o endereco do server e porta do servidor

      //envia o pacote

      // fecha o cliente
      clientSocket.close();
   }
}
