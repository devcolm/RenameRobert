package ui;

import core.ApplicationData;
import core.RenameController;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.details.MedalReadableDateDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static core.ApplicationData.DEFAULT_BROWSE_DIRECTORY;

public class MainView extends RenameRobertView {

    private static final Logger LOGGER = LogManager.getLogger();

    private JPanel contentPane;
    private JButton renameFilesButton;
    private JButton browseButton;
    private JLabel pictureLabel;
    private JLabel selectFilesLabel;
    private JLabel selectAlgorithmLabel;
    private JList algorithmList;

    private final JFileChooser fileChooser = new JFileChooser();


    public MainView(ApplicationData applicationData, RenameController renameController) {
        super(applicationData, renameController);
    }

    @Override
    protected void initialise() {
        setWindowDefaults(contentPane);
        this.setTitle(ApplicationData.APPLICATION_NAME);
        this.setSize(420, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    protected void configureAppearance() {
        setFonts();
        setIcons();
    }

    @Override
    protected void configureActions() {
        setFileChooser();
        addListeners();
    }

    private void setFonts() {
        selectFilesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectAlgorithmLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void setIcons() {
        BufferedImage image = applicationData.getImage();
        if (image == null) {
            pictureLabel.setText("Robert is working from home today.");
            return;
        }

        ImageIcon robertIcon = new ImageIcon();
        robertIcon.setImage(image);
        pictureLabel.setIcon(robertIcon);

        this.setIconImage(image);
    }

    private void setFileChooser() {
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setCurrentDirectory(new File(
                ObjectUtils.firstNonNull(applicationData.getRecentDirectory(), DEFAULT_BROWSE_DIRECTORY)));
    }

    private void addListeners() {
        browseButton.addActionListener(e -> {
            fileChooser.showOpenDialog(browseButton);
            var files = fileChooser.getSelectedFiles();
            renameController.setSelectedFiles(files);
            applicationData.setRecentDirectory(fileChooser.getCurrentDirectory().getPath());
            LOGGER.info("Selected ({}) files to rename.", files.length);
        });

        renameFilesButton.addActionListener(e -> {
            // todo based on selected algorithm
            new MedalReadableDateDetailsView(applicationData, renameController).open();

            LOGGER.info("Renaming files...");
            //renameController.execute();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                applicationData.save();
            }
        });
    }


}
