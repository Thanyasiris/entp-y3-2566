import java.io.*;
import java.net.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class UDPServer {
    public static void main(String[] args) throws Exception {
        // Create datagram socket at port 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] recieveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            // Create space for recieved datagram
            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
            // Recieve datagram
            serverSocket.receive(recievePacket);

            String sentence = new String(recievePacket.getData());

            // Get IP addr port #, of sender
            InetAddress iPAddress = recievePacket.getAddress();
            int port = recievePacket.getPort();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            String capitalizedSentence = dtf.format(now);
            sendData = capitalizedSentence.getBytes();

            // Create datagram to send to client
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, iPAddress, port);

            // Write out datagram to socket
            serverSocket.send(sendPacket);
        } // End of while loop, loop back and wait for another datagram
    }
}
