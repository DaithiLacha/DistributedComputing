package server;

import protocol.Protocol;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static protocol.Protocol.CONNECT;

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
                determineMessageType(message);
                System.out.println("message received: " + message);
//                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
//                    System.out.println("Session over.");
//                    myDataSocket.close();
//                    isSessionRunning = false;
//                }
//                else {
//                    myDataSocket.sendMessage(message);
//                }
            }
        } catch (Exception ex) {
            System.out.println("Exception caught in thread: " + ex);
        }
    }

    public static boolean credentialsAreValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (username.equals(line.substring(0, line.indexOf(':'))) && password.equals(line.substring(line.indexOf(':') + 2))) {
                    Server.loggedInUser = username;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void determineMessageType(String message) {
        ArrayList<String> messageType = new ArrayList<>();
        messageType = (ArrayList<String>) Arrays.asList(message.split(";"));

        switch (messageType.get(1)) {
            case "CONNECT":

            case "LOGIN":
                if(credentialsAreValid(messageType.get(2), messageType.get(3))) {
                    try {
                        myDataSocket.sendMessage("502: Login Successful");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }
    }
}
