package client;

import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 *
 * @author M. L. Liu
 */
class Client {
    private static final String TERMINATION_MESSAGE = "end";
    private InputStreamReader is = new InputStreamReader(System.in);
    private BufferedReader br = new BufferedReader(is);

    private String retrieveHostName() {
        System.out.println("Welcome to the Echo client.\nWhat is the name of the server host?");
        String hostName = "";
        try {
            hostName = br.readLine();
            if (hostName.length() == 0)
                hostName = "localhost";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hostName;
    }

    private String retrievePortNumber() {
        System.out.println("What is the port number of the server host?");
        String portNum = "";
        try {
            portNum = br.readLine();
            if (portNum.length() == 0) {
                portNum = "8055";
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return portNum;
    }

    void runClient() {
        try {
            String hostName = retrieveHostName();
            String portNum = retrievePortNumber();
            EchoClientHelper2 helper = new EchoClientHelper2(hostName, portNum);
            boolean done = false;
            String message, echo;
            while (!done) {
                System.out.println("Enter a line to receive an echo from the server, or a single period to quit.");
                message = br.readLine();
                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
                    done = true;
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
}