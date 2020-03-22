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
                String serverResponse = Client.downloadMessagesRequest(Protocol.DOWNLOAD, SelectOptionScreen.super.getTitle());
                downloadGUI(serverResponse);
            }
        });

        btnLogOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String serverResponse = Client.logOffRequest(Protocol.LOGOFF, SelectOptionScreen.super.getTitle());
                logOffGUI(serverResponse);
                SelectOptionScreen.super.setVisible(false);
                SelectOptionScreen.super.dispose();
                ConnectionScreen connectionScreen = new ConnectionScreen();
                connectionScreen.setVisible(true);
            }
        });
    }

    private void downloadGUI(String response) {
        if(response.equals("702: " + Protocol.DOWNLOAD_SUCCESS)) {
            JOptionPane.showMessageDialog(null, "Download Successful",
                    response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Download UnSuccessful",
                    response, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void logOffGUI(String response) {
        if(response.equals("902: " + Protocol.LOGOFF_SUCCESS)) {
            JOptionPane.showMessageDialog(null, SelectOptionScreen.super.getTitle() +
                    " has been logged off", response, JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "There was an error in logging off the user",
                    response, JOptionPane.ERROR_MESSAGE);
        }
    }
}
