package clientGUI;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        btnUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
