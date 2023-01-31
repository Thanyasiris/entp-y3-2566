import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        String sentence;
        String modifiedSentence;

        // Create input stream
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Create client socket connect to server
        Socket clientSocket = new Socket("localhost", 1667);

        // Create output stream attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine();

        // Sent line to server
        outToServer.writeBytes(sentence + "\n");

        // Read line from server
        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }
}