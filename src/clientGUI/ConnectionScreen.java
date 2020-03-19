package clientGUI;

import javax.swing.*;
import client.*;

import java.io.IOException;

public class ConnectionScreen extends JFrame {
    private JLabel lblMain;
    private JPanel panelMain;
    private JTextField txtHostName;
    private JTextField txtPortNo;
    private JButton btnSubmit;
    private JLabel lblHostName;
    private JLabel lblPortNo;


    public ConnectionScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        btnSubmit.addActionListener(e -> {
            Client client = new Client();
            client.runClient(txtHostName.getText(), txtPortNo.getText());
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
            this.setVisible(false);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        ConnectionScreen connectionScreen = new ConnectionScreen();
        connectionScreen.setVisible(true);
    }


}
