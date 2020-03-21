package clientGUI;

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
                SelectOptionScreen.super.setVisible(false);
                SelectOptionScreen.super.dispose();
            }
        });

        downloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "UserMessages/" + Server.loggedInUser + ".txt");
                try {
                    pb.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
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
