package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class RenameRobertUI extends JFrame {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TITLE = "Rename Robert";

    private JPanel contentPane;
    private JButton renameFilesButton;
    private JButton browseButton;
    private JLabel pictureLabel;
    private JLabel selectFilesLabel;
    private JLabel selectAlgorithmLabel;
    private JList algorithmList;

    public void initialise() {
        this.setContentPane(contentPane);
        this.setTitle(TITLE);
        this.setResizable(false);
        this.setSize(420, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setFonts();
        setIcons();
    }

    private void setFonts() {
        selectFilesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectAlgorithmLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void setIcons() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("icons/RenameRobert.jpg");

        BufferedImage image;
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            LOGGER.error("Error loading application image: ", e);
            pictureLabel.setText("Robert is working from home today.");
            return;
        }

        ImageIcon robertIcon = new ImageIcon();
        robertIcon.setImage(image);
        pictureLabel.setIcon(robertIcon);

        this.setIconImage(image);
    }

}
