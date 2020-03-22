package client;

import server.Server;

import java.io.*;
import java.util.Scanner;

/**
 * This module contains the presentation logic of an Echo Client.
 *
 * @author M. L. Liu
 */
public class Client {
    private static final String TERMINATION_MESSAGE = "end";
    private InputStreamReader is = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(is);
    public static ClientHelper clientHelper;

    public String setHostName(String host) {
        if(host.equals("")) {
            return "localhost";
        }else {
            return host;
        }
    }

    public String setPortNumber(String num) {
        if(num.equals("")) {
            return "8055";
        }else {
            return num;
        }
    }

    public void runClient(String host, String port) {
        try {
            clientHelper = new ClientHelper(setHostName(host), setPortNumber(port));
            boolean isTerminated = false;
            boolean isLoggedIn = false;
            String message, echo, username, password;
            Scanner input = new Scanner(System.in);


//            while (!isLoggedIn) {
//                if (login(username, password)) {
//                    isLoggedIn = true;
//                } else {
//                    System.out.println("Incorrect Login Details. Re-enter Username: ");
//                    username = input.nextLine();
//                    System.out.println("Enter Password: ");
//                    password = input.nextLine();
//                }
//            }

//            Server.createUser();

//            while (!isTerminated) {
//                System.out.println("Enter a line to receive an echo from the server. Type 'end' to quit.");
//                message = br.readLine();
//                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
//                    isTerminated = true;
//                    helper.done();
//                } else {
//                    echo = helper.helperSendRequest(message);
//                    System.out.println(echo);
//                }
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String createUser(protocol.Protocol proto, String username, String password) {
        String clientRequest = "601;" + proto + ";" + username + ";" + password;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String login(protocol.Protocol proto, String username, String password) {
        String clientRequest = "501;" + proto + ";" + username + ";" + password;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String downloadMessages(protocol.Protocol proto, String username) {
        String clientRequest = "701;" + proto + ";" + username;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    public static String uploadMessage(protocol.Protocol proto, String username, String message) {
        String clientRequest = "801;" + proto + ";" + username + ";" + message;
        String serverResponse = "";

        try {
            serverResponse = clientHelper.helperSendRequest(clientRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return serverResponse;
    }
}