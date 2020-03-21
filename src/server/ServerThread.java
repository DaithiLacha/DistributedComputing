package server;

import protocol.Protocol;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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
            while(isSessionRunning) {
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

    private void loginServerSide(List<String> messageType) {
        if(credentialsAreValid(messageType.get(2), messageType.get(3))) {
            try {
                myDataSocket.sendMessage("502: " + Protocol.LOGIN_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                myDataSocket.sendMessage("503: " + Protocol.LOGIN_FAILURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean doesUserNameExist(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (username.equals(line.substring(0, line.indexOf(':')))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createUserServerSide(List<String> messageType) {
        String username = messageType.get(2);
        String password = messageType.get(3);
        if(doesUserNameExist(username)) {
            try {
                myDataSocket.sendMessage("603: " + Protocol.CREATE_USER_FAILURE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8));
                JOptionPane.showMessageDialog(null, username + ": " + password);
                writer.newLine();
                writer.append(username).append(": ").append(password);
                writer.close();
                String fileName = username + ".txt";
                File directory = new File("UserMessages");
                if(!directory.exists()) {
                    directory.mkdir();
                }
                File file = new File("UserMessages/" + fileName);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.close();
                myDataSocket.sendMessage("602: " + Protocol.CREATE_USER_SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void determineMessageType(String message) {
        List<String> messageType = Arrays.asList(message.split(";"));

        switch (messageType.get(1)) {
            case "CONNECT":
                return;
            case "LOGIN":
                loginServerSide(messageType);
            case "CREATE_USER":
                createUserServerSide(messageType);
        }
    }
}
