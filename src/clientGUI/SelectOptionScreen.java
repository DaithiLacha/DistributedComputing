package clientGUI;

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

    public SelectOptionScreen() {
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

        downloadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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

    public static void main(String[] args) {
        SelectOptionScreen gui = new SelectOptionScreen();
        gui.setVisible(true);
    }
}
