package client;

import java.io.*;

/**
 * This module contains the presentaton logic of an Echo Client.
 *
 * @author M. L. Liu
 */
public class EchoClient2 {
    static final String TERMINATION_MESSAGE = "end";

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            System.out.println("Welcome to the Echo client.\nWhat is the name of the server host?");
            String hostName = br.readLine();
            if (hostName.length() == 0)
                hostName = "localhost";
            System.out.println("What is the port number of the server host?");
            String portNum = br.readLine();
            if (portNum.length() == 0)
                portNum = "7";
            EchoClientHelper2 helper = new EchoClientHelper2(hostName, portNum);
            boolean done = false;
            String message, echo;
            while (!done) {
                System.out.println("Enter a line to receive an echo from the server, or a single period to quit.");
                message = br.readLine();
                if ((message.trim()).equals(TERMINATION_MESSAGE)) {
                    done = true;
                    helper.done();
                }else {
                    echo = helper.getEcho(message);
                    System.out.println(echo);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}