package clientGUI;

import client.Client;
import protocol.Protocol;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SelectOptionScreen extends JFrame{
    private JButton btnUpload;
    private JButton downloadButton;
    private JPanel panelMain;
    private JLabel lblUpload;
    private JLabel lblDownload;
    private JLabel lblLogOff;
    private JButton btnLogOff;

    SelectOptionScreen() {
        super();
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        btnUpload.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                UploadMessageScreen uploadMessageScreen = new UploadMessageScreen();
                uploadMessageScreen.setVisible(true);
                uploadMessageScreen.setTitle(SelectOptionScreen.super.getTitle());
                SelectOptionScreen.super.dispose();
            }
        });

        downloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.downloadMessages(Protocol.DOWNLOAD, SelectOptionScreen.super.getTitle());
                if(serverResponse.equals("702: " + Protocol.DOWNLOAD_SUCCESS)) {
                    JOptionPane.showMessageDialog(null, "Download Successful", serverResponse, JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Download UnSuccessful", serverResponse, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnLogOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(-1);
            }
        });
    }
}
