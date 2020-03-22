package clientGUI;

import client.Client;
import protocol.Protocol;
import server.Server;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SelectOptionScreen extends JFrame{
    private JButton btnUpload;
    private JButton downloadButton;
    private JPanel panelMain;
    private JLabel lblUpload;
    private JLabel lblDownload;
    private JLabel lblLogOff;
    private JButton btnLogOff;

    public SelectOptionScreen() {
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
                    ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "UserMessages/" + SelectOptionScreen.super.getTitle() + ".txt");
                    try {
                        pb.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
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
