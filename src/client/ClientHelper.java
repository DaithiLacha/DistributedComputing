package client;

import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 *
 * @author M. L. Liu
 */

class ClientHelper {
    private static final String TERMINATION_MESSAGE = "end";
    private ClientStreamSocket mySocket;

    ClientHelper(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        this.mySocket = new ClientStreamSocket(serverHost, serverPort);
        System.out.println("Connection request made");
    }

    String getEcho(String message) throws IOException {
        String echo;
        mySocket.sendMessage(message);
        echo = mySocket.receiveMessage();
        return echo;
    }

    void done() throws IOException {
        mySocket.sendMessage(TERMINATION_MESSAGE);
        mySocket.close();
    }
}
