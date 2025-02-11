package clientGUI;

import javax.swing.*;
import client.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConnectionScreen extends JFrame {
    private JLabel lblMain;
    private JPanel panelMain;
    private JTextField txtHostName;
    private JTextField txtPortNo;
    private JButton btnSubmit;
    private JLabel lblHostName;
    private JLabel lblPortNo;


    ConnectionScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Client client = new Client();
                client.runClient(txtHostName.getText(), txtPortNo.getText());
                LoginOrCreateUserScreen gui = new LoginOrCreateUserScreen();
                ConnectionScreen.super.setVisible(false);
                ConnectionScreen.super.dispose();
            }
        });
    }

    public static void main(String[] args) {
        ConnectionScreen connectionScreen = new ConnectionScreen();
    }


}
