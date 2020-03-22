package clientGUI;

import client.Client;
import client.ClientHelper;
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
                String serverResponse = Client.logOffRequest(Protocol.LOGOFF, SelectOptionScreen.super.getTitle());
                if(serverResponse.equals("902: " + Protocol.LOGOFF_SUCCESS)) {
                    JOptionPane.showMessageDialog(null, SelectOptionScreen.super.getTitle() + " has been logged off",
                            serverResponse, JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "There was an error in logging off the user",
                            serverResponse, JOptionPane.ERROR_MESSAGE);
                }
                SelectOptionScreen.super.setVisible(false);
                SelectOptionScreen.super.dispose();
                ConnectionScreen connectionScreen = new ConnectionScreen();
                connectionScreen.setVisible(true);
            }
        });
    }
}
