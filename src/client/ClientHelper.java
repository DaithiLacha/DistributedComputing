package client;

import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 *
 * @author M. L. Liu
 */

public class ClientHelper {
    private static final String TERMINATION_MESSAGE = "end";
    private static ClientStreamSocket mySocket;

    public ClientHelper(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        this.mySocket = new ClientStreamSocket(serverHost, serverPort);
        System.out.println("Connection request made");
    }

    public String helperSendRequest(String message) throws IOException {
        String response;
        mySocket.socketSendRequest(message);
        response = mySocket.socketReceiveRequest();
        return response;
    }

    public void done() throws IOException {
        mySocket.socketSendRequest(TERMINATION_MESSAGE);
        mySocket.close();
    }
}
