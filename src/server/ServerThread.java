package server;

/**
 * This module is to be used with a concurrent Echo server.
 * Its run method carries out the logic of a client session.
 *
 * @author M. L. Liu
 */

public class ServerThread implements Runnable {
    private static final String TERMINATION_MESSAGE = "end";
    private ServerStreamSocket myDataSocket;

    ServerThread(ServerStreamSocket myDataSocket) {
        this.myDataSocket = myDataSocket;
    }

    public void run() {
        boolean isSessionRunning = true;
        String message;
        try {
            while (isSessionRunning) {
                message = myDataSocket.receiveMessage();
                System.out.println("message received: " + message);
                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
                    System.out.println("Session over.");
                    myDataSocket.close();
                    isSessionRunning = false;
                }
                else {
                    myDataSocket.sendMessage(message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception caught in thread: " + ex);
        }
    }
}
