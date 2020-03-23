package clientGUI;

import client.Client;
import protocol.Protocol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class LoginScreen extends JFrame{
    private JPanel lblUserName;
    private JPanel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSubmit;
    private JPanel panelMain;

    LoginScreen() {
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
                String response;
                if(txtUsername.getText().length() == 0) {
                    response = Client.loginRequest(Protocol.LOGIN, " ", new String(txtPassword.getPassword()));
                }else if(txtPassword.getPassword().length == 0) {
                    response = Client.loginRequest(Protocol.LOGIN, txtUsername.getText(), " ");
                }else {
                    response = Client.loginRequest(Protocol.LOGIN, txtUsername.getText(), new String(txtPassword.getPassword()));
                }
                loginResponseGUI(response);
            }
        });
    }

    private void loginResponseGUI(String response) {
        if(response.equals("502: " + Protocol.LOGIN_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Welcome: " + txtUsername.getText(),
                    response, JOptionPane.INFORMATION_MESSAGE);
            SelectOptionScreen selectOptionScreen = new SelectOptionScreen();
            selectOptionScreen.setTitle(txtUsername.getText());
            LoginScreen.super.setVisible(false);
            LoginScreen.super.dispose();
        }else if(response.equals("503: " + Protocol.LOGIN_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Invalid Details Entered",
                    response, JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }else {
            JOptionPane.showMessageDialog(null, "You are already logged in",
                    response, JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }
}
