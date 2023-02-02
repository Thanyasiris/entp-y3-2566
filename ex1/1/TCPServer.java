import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        String clientSentence;
        String capitalizedSentence;

        System.out.println("Server\nWaiting for client connection at port number 1667");

        // Create welcoming socket at port 1667
        ServerSocket welcomeSocket = new ServerSocket(1667);

        while (true) {
            // Wait, on welcoming socket for contact by client
            Socket connectionSocket = welcomeSocket.accept();
            // Create input stream, attached to socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            // Create output stream, attached to socket
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            // Read the line from socked
            clientSentence = inFromClient.readLine();
            String[] arrOfStr = clientSentence.split(",", 2);
            capitalizedSentence = "The result is " + (Integer.parseInt(arrOfStr[0]) + Integer.parseInt(arrOfStr[1]))
                    + "\n";
            // Write out line to socket
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
