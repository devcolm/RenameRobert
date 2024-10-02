package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class RenameRobertUI extends JFrame {

    private JPanel contentPane;
    private JButton renameFilesButton;
    private JList algorithmList;
    private JButton browseButton;
    private JLabel pictureLabel;
    private JLabel selectFilesLabel;
    private JLabel selectAlgorithmLabel;

    public static JFrame create() {
        // Fixes scaling issue
        // @ https://github.com/kirill-grouchnikov/substance/issues/48
        System.setProperty("sun.java2d.uiScale", "1.0");

        RenameRobertUI renameRobertUI = new RenameRobertUI();
        renameRobertUI.setContentPane(renameRobertUI.contentPane);
        renameRobertUI.setTitle("Rename Robert");
        renameRobertUI.setResizable(false);
        renameRobertUI.setSize(420, 500);
        renameRobertUI.setLocationRelativeTo(null);
        renameRobertUI.setVisible(true);
        renameRobertUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        renameRobertUI.setPicture();

        renameRobertUI.selectFilesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        renameRobertUI.selectAlgorithmLabel.setFont(new Font("Arial", Font.BOLD, 16));


        return renameRobertUI;
    }

    private void setPicture(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("icons/RenameRobert.jpg");
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {

        }

        ImageIcon picture = new ImageIcon();
        picture.setImage(image);
        pictureLabel.setIcon(picture);
    }

}
