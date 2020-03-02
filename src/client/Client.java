package client;

import server.Server;

import java.io.*;
import java.util.Scanner;

/**
 * This module contains the presentaton logic of an Echo Client.
 *
 * @author M. L. Liu
 */
public class Client {
    private static final String TERMINATION_MESSAGE = "end";
    private InputStreamReader is = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(is);

    private String retrieveHostName() {
        System.out.println("Welcome to the Echo client.\nEnter server host name?");
        String hostName = "";
        try {
            hostName = br.readLine();
            if (hostName.length() == 0) {
                hostName = "localhost";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    private String retrievePortNumber() {
        System.out.println("Enter server port number: ");
        String portNum = "";
        try {
            portNum = br.readLine();
            if (portNum.length() == 0) {
                portNum = "8055";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return portNum;
    }

    void runClient() {
        try {
            String hostName = retrieveHostName();
            String portNum = retrievePortNumber();
            ClientHelper helper = new ClientHelper(hostName, portNum);
            boolean isTerminated = false;
            boolean isLoggedIn = false;
            String message, echo, username, password;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Username: ");
            username = input.nextLine();
            System.out.println("Enter Password: ");
            password = input.nextLine();

            while (!isLoggedIn) {
                if (login(username, password)) {
                    isLoggedIn = true;
                } else {
                    System.out.println("Incorrect Login Details. Re-enter Username: ");
                    username = input.nextLine();
                    System.out.println("Enter Password: ");
                    password = input.nextLine();
                }
            }

//            Server.createUser();

            while (!isTerminated) {
                System.out.println("Enter a line to receive an echo from the server. Type 'end' to quit.");
                message = br.readLine();
                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
                    isTerminated = true;
                    helper.done();
                } else {
                    echo = helper.getEcho(message);
                    System.out.println(echo);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean login(String username, String password) {
        return Server.credentialsAreValid(username, password);
    }
}