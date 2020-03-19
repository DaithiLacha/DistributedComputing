package clientGUI;

import client.Client;
import protocol.Protocol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static server.Server.loggedInUser;

public class LoginScreen extends JFrame{
    private JPanel lblUserName;
    private JPanel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSubmit;
    private JPanel panelMain;

    public LoginScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String response = Client.login(Protocol.LOGIN, txtUsername.getText(), new String(txtPassword.getPassword()));
                if(response.equals("502: Login Successful")) {
                    JOptionPane.showMessageDialog(null, "Welcome: " + txtUsername.getText(),
                            "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    loggedInUser = txtUsername.getText();
                    SelectOptionScreen selectOptionScreen = new SelectOptionScreen();
                    selectOptionScreen.setVisible(true);
                    LoginScreen.super.setVisible(false);
                    LoginScreen.super.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Details Entered",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginScreen gui = new LoginScreen();
        gui.setVisible(true);
    }
}
