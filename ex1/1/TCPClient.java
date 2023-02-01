import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        String sentence;
        String modifiedSentence;
        int num1, num2;
        Scanner sc = new Scanner(System.in);

        // Create input stream
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Create client socket connect to server
        Socket clientSocket = new Socket("localhost", 1667);

        // Create output stream attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Client\n");
        System.out.println("enter number 1 (to end just press enter): ");
        String input1 = inFromUser.readLine();
        if (input1 != "") {
            num1 = Integer.parseInt(input1);
            System.out.println("enter number 2 (to end just press enter): ");
            String input2 = inFromUser.readLine();
            if (input2 != "") {
                num2 = Integer.parseInt(input2);

                // sentence = inFromUser.readLine();

                // Sent line to server
                outToServer.writeBytes(input1);
                modifiedSentence = inFromServer.readLine();
                outToServer.writeBytes(input2);

                // Read line from server
                modifiedSentence = inFromServer.readLine();

                System.out.println("FROM SERVER: " + modifiedSentence);
            }
        }
        clientSocket.close();
    }
}
