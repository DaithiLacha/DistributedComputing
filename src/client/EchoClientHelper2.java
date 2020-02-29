package client;

import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 *
 * @author M. L. Liu
 */

class EchoClientHelper2 {
    private static final String endMessage = ".";
    private ClientStreamSocket mySocket;

    EchoClientHelper2(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        this.mySocket = new ClientStreamSocket(serverHost, serverPort);
        System.out.println("Connection request made");
    }

    String getEcho(String message) throws IOException {
        String echo = "";
        mySocket.sendMessage(message);
        echo = mySocket.receiveMessage();
        return echo;
    }

    void done() throws IOException {
        mySocket.sendMessage(endMessage);
        mySocket.close();
    }
}
