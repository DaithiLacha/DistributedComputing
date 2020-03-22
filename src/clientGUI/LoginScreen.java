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

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtUsername.getText().length() == 0) {
                    txtUsername.setText(" ");
                }
                if(txtPassword.getPassword().length == 0) {
                    txtPassword.setText(" ");
                }
                String response = Client.login(Protocol.LOGIN, txtUsername.getText(), new String(txtPassword.getPassword()));
                if(response.equals("502: " + Protocol.LOGIN_SUCCESS)) {
                    JOptionPane.showMessageDialog(null, "Welcome: " + txtUsername.getText(),
                            "502: " + Protocol.LOGIN_SUCCESS, JOptionPane.INFORMATION_MESSAGE);
                    SelectOptionScreen selectOptionScreen = new SelectOptionScreen();
                    selectOptionScreen.setVisible(true);
                    selectOptionScreen.setTitle(txtUsername.getText());
                    LoginScreen.super.setVisible(false);
                    LoginScreen.super.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Details Entered",
                            "503: " + Protocol.LOGIN_FAILURE, JOptionPane.ERROR_MESSAGE);
                    txtUsername.setText("");
                    txtPassword.setText("");
                }
            }
        });
    }
}
