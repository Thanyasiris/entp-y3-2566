import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String[] args) throws Exception {
        String modifiedSentence;

        // Create input stream
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Create client socket connect to server
        Socket clientSocket = new Socket("localhost", 1667);

        // Create output stream attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // Create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Client");
        System.out.print("enter number 1 (to end just press enter): ");
        String input1 = inFromUser.readLine();
        // if (input1 != "") {
        try {
            Integer.parseInt(input1);
            System.out.print("enter number 2 (to end just press enter): ");
            String input2 = inFromUser.readLine();
            try {
                Integer.parseInt(input2);

                // Sent line to server
                outToServer.writeBytes(input1 + "," + input2 + "\n");
                modifiedSentence = inFromServer.readLine();

                System.out.println(modifiedSentence);
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
        clientSocket.close();
    }
}
