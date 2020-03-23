package clientGUI;

import client.Client;
import protocol.Protocol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UploadMessageScreen extends JFrame {
    private JTextArea txtMessage;
    private JPanel panelMain;
    private JButton btnUpload;
    private JButton btnBack;
    private JLabel lblMessage;
    private JLabel lblUploadMessage;

    UploadMessageScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);

        btnUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.uploadMessageRequest(Protocol.UPLOAD,
                        UploadMessageScreen.super.getTitle(), txtMessage.getText());
                uploadGUI(serverResponse);
            }
        });
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UploadMessageScreen.super.setVisible(false);
                UploadMessageScreen.super.dispose();
                SelectOptionScreen selectOptionScreen = new SelectOptionScreen();
                selectOptionScreen.setTitle(UploadMessageScreen.super.getTitle());
            }
        });
    }

    private void uploadGUI(String response) {
        if(response.equals("802: " + Protocol.UPLOAD_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Message has been uploaded",
                    response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Message failed to upload",
                    response, JOptionPane.ERROR_MESSAGE);
        }
        txtMessage.setText("");
    }
}
