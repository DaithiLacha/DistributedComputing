package clientGUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginOrCreateUserScreen extends JFrame {
    private JButton btnLogin;
    private JButton btnCreateUser;
    private JLabel lblChooseOption;
    private JPanel panelMain;

    LoginOrCreateUserScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();


        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                LoginOrCreateUserScreen.super.setVisible(false);
                LoginOrCreateUserScreen.super.dispose();
            }
        });


        btnCreateUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CreateUserScreen createUserScreen = new CreateUserScreen();
                createUserScreen.setVisible(true);
                LoginOrCreateUserScreen.super.setVisible(false);
                LoginOrCreateUserScreen.super.dispose();
            }
        });
    }
}
