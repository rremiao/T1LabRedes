// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a porta deste cliente
// Imprime o dado na tela

import java.io.*;
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
         DatagramSocket serverSocket = new DatagramSocket(8668);
         String response = "Servidor respondendo";

            byte[] receiveData = new byte[1024];
            int contador = 0;
            boolean isRunning = true;
            
            while(isRunning)
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


                  System.out.println("Mensagem recebida: " + sentence);

                  if(null != sentence && sentence.contains("FIM")) {
                     response = "FIM";
                  }
               
                  byte[] sendData = new byte[1024];
                  

                  if(contador >= 5) {
                     response = "";
                     response = "FIM";   
                     System.out.println(contador);                  
                  }

                  sendData = response.getBytes();
                  System.out.println(sendData.length); 
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, receivePort);

                  serverSocket.send(sendPacket); 
                  contador++;

                  if(response.contains("FIM")) {
                     isRunning = false;
                  }
               }
      }
}
