// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a porta deste cliente
// Imprime o dado na tela

import java.net.*;

class UDPServer {
   public static void main(String args[])  throws Exception
      {
         if (args.length < 1) {
            System.out.println("Usage: java UDPServer <port>");
            return;
         }

         int port = Integer.parseInt(args[0]);

         // cria socket do servidor com a porta 9876
         DatagramSocket serverSocket = new DatagramSocket(port);

            byte[] receiveData = new byte[1024];
            while(true)
               {
                  // declara o pacote a ser recebido
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                  // recebe o pacote do cliente
                  serverSocket.receive(receivePacket);

                  // pega os dados, o endereco IP e a porta do cliente
                  // para poder mandar a msg de volta
                  String sentence = new String(receivePacket.getData());
                  InetAddress IPAddress = receivePacket.getAddress();
                  int receivePort = receivePacket.getPort();

                  System.out.println(sentence);
               }
      }
}
