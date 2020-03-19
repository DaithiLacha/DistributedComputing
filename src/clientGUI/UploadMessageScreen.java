package clientGUI;

import javax.swing.*;

public class UploadMessageScreen extends JFrame {
    private JTextArea txtMessage;
    private JPanel panelMain;
    private JButton btnUpload;
    private JButton btnBack;
    private JLabel lblMessage;
    private JLabel lblUploadMEssage;

    public UploadMessageScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    public static void main(String[] args) {
        UploadMessageScreen gui = new UploadMessageScreen();
        gui.setVisible(true);
    }
}
