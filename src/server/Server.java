package server;

import java.net.*;

/**
 * This module contains the application logic of an echo server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 *
 * @author M. L. Liu
 */

public class Server {
    public static void main(String[] args) {
        int serverPort = 5417;

        try {
            ServerSocket myConnectionSocket = new ServerSocket(serverPort);
            System.out.println("Echo server ready.");
            while (true) {
                System.out.println("Waiting for a connection.");
                MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
                System.out.println("connection accepted");
                Thread theThread = new Thread(new ServerThread(myDataSocket));
                theThread.start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
