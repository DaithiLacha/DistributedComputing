package client;

import server.MyStreamSocket;

import java.net.*;
import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using stream-mode socket.
 *
 * @author M. L. Liu
 */


public class EchoClientHelper2 {

    static final String endMessage = ".";
    private MyStreamSocket mySocket;
    private InetAddress serverHost;
    private int serverPort;

    EchoClientHelper2(String hostName, String portNum) throws IOException {
        this.serverHost = InetAddress.getByName(hostName);
        this.serverPort = Integer.parseInt(portNum);
        this.mySocket = new MyStreamSocket(this.serverHost, this.serverPort);
        System.out.println("Connection request made");
    }

    public String getEcho(String message) throws IOException {
        String echo = "";
        mySocket.sendMessage(message);
        echo = mySocket.receiveMessage();
        return echo;
    }

    public void done() throws IOException {
        mySocket.sendMessage(endMessage);
        mySocket.close();
    }
}
