import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String[] args) throws Exception {

        // Create client socket
        DatagramSocket clientSocket = new DatagramSocket();

        // Translate hostname to IP addressing using DNS
        InetAddress iPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] recieveData = new byte[1024];

        String sentence = "";
        sendData = sentence.getBytes();

        // Create datagram with data-to-send, length, IP addr, port
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, iPAddress, 9876);

        // Send datagram to server
        clientSocket.send(sendPacket);

        DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);

        // Read datagram from server
        clientSocket.receive(recievePacket);

        String modifiedSentence = new String(recievePacket.getData());

        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}
