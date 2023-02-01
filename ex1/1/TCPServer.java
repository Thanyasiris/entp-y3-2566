import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String[] args) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        int prevNum = 0;
        int num = 0;
        int count = 0;

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
            num = Integer.parseInt(clientSentence);
            count++;
            if (count == 1) {
                prevNum = num;
                outToClient.writeBytes("");
            }
            if (count == 2) {
                capitalizedSentence = "The result is " + (prevNum + num) + "\n";
                // Write out line to socket
                outToClient.writeBytes(capitalizedSentence);
                prevNum = 0;
                count = 0;
            }
        }
    }
}
