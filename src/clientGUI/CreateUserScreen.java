package clientGUI;

import client.Client;
import protocol.Protocol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateUserScreen extends JFrame {
    private JPasswordField txtPassword;
    private JTextField txtUserName;
    private JPasswordField txtConfirm;
    private JLabel lblUserName;
    private JLabel lblPassword;
    private JLabel lblConfirm;
    private JButton btnSubmit;
    private JButton btnBack;
    private JPanel panelMain;


    CreateUserScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String username = txtUserName.getText();
                String pass = new String(txtPassword.getPassword());
                String confirm = new String(txtConfirm.getPassword());
                if(areFieldsValid(username, pass, confirm)) {
                    String serverResponse = Client.createUserRequest(Protocol.CREATE_USER, username, pass);
                    createUserResponseGUI(serverResponse);
                }

            }
        });

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateUserScreen.super.setVisible(false);
                CreateUserScreen.super.dispose();
                LoginOrCreateUserScreen loginOrCreateUserScreen = new LoginOrCreateUserScreen();
                loginOrCreateUserScreen.setVisible(true);
            }
        });
    }

    private boolean areFieldsValid(String username, String pass, String confirm) {
        if(username.length() > 0) {
            if(pass.equals(confirm)) {
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "Passwords do not Match. Please Re-Enter details",
                        "Invalid Details", JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                txtConfirm.setText("");
                return false;
            }
        }else {
            JOptionPane.showMessageDialog(null, "Please enter a username",
                    "Invalid Details", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtConfirm.setText("");
            return false;
        }
    }

    private void createUserResponseGUI(String serverResponse) {
        if(serverResponse.equals("603: " + Protocol.CREATE_USER_FAILURE)) {
            JOptionPane.showMessageDialog(null, "Username already exists in the system please choose another",
                    serverResponse, JOptionPane.ERROR_MESSAGE);
            txtUserName.setText("");
            txtPassword.setText("");
            txtConfirm.setText("");
        }else if(serverResponse.equals("602: " + Protocol.CREATE_USER_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "User: " + txtUserName.getText() + " created",
                    serverResponse, JOptionPane.INFORMATION_MESSAGE);
            txtUserName.setText("");
            txtPassword.setText("");
            txtConfirm.setText("");
        }
    }
}
