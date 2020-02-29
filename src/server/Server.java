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

class Server {
    void deployServer(){
        try {
            final int SERVER_PORT = 8055;
            ServerSocket myConnectionSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Echo server ready.");
            while (true) {
                System.out.println("Waiting for a connection.");
                ServerStreamSocket myDataSocket = new ServerStreamSocket(myConnectionSocket.accept());
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
