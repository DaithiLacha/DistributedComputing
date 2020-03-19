package client;

import server.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ClientStreamSocket {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    ClientStreamSocket(InetAddress acceptorHost, int acceptorPort) throws IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

    private void setStreams() throws IOException {
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }

    String socketReceiveRequest() throws IOException {
        return input.readLine();
    }

    void socketSendRequest(String message) throws IOException {
        output.print(message + "\n");
//        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Server.loggedInUser + "/" + Server.loggedInUser + ".txt", true), StandardCharsets.UTF_8))) {
//            bw.newLine();
//            bw.append(message);
//            bw.newLine();
//            bw.append("=====================================================================================");
//            bw.newLine();
//        }catch (IOException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
        output.flush();
    }

    void close() throws IOException {
        socket.close();
    }
}
