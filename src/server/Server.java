package server;

import client.Client;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * This module contains the application logic of an echo server
 * which uses a stream-mode socket for interprocess communication.
 * Unlike EchoServer2, this server services clients concurrently.
 * A command-line argument is required to specify the server port.
 *
 * @author M. L. Liu
 */

public class Server {
    public static String loggedInUser = "";
    void deployServer(){
        try {
            final int SERVER_PORT = 8055;
            ServerSocket myConnectionSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server ready.");
            while(true) {
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

    public static boolean credentialsAreValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (username.equals(line.substring(0, line.indexOf(':'))) && password.equals(line.substring(line.indexOf(':') + 2))) {
                    loggedInUser = username;
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createUser() {
        String username, password;
        boolean isCreated = false;
        Scanner input = new Scanner(System.in);
        while (!isCreated) {
            System.out.println("Enter Username: ");
            username = input.nextLine();
            System.out.println("Enter Password: ");
            password = input.nextLine();
            if (!Client.login(username, password)) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8))) {
                    writer.newLine();
                    writer.append(username).append(": ").append(password);
                    String directoryName = username;
                    String fileName = username + ".txt";
                    File directory = new File(directoryName);
                    if(!directory.exists()) {
                        directory.mkdir();
                    }
                    File file = new File(directoryName + "/" + fileName);
                    try {
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.close();
                    }catch (IOException e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isCreated = true;
            } else {
                System.out.println("User already exists!!!");
            }
        }
    }
}
