package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

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

    String ReceiveResponse() throws IOException {
        return input.readLine();
    }

    void socketSendRequest(String message) throws IOException {
        output.print(message + "\n");
        output.flush();
    }
}
