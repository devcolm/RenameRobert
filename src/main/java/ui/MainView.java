package ui;

import algorithms.RenameAlgorithmType;
import core.ApplicationData;
import core.RenameController;
import org.apache.commons.lang3.NotImplementedException;
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
import java.util.Arrays;

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
    private JLabel selectedFilesInfoField;

    // TODO: Guice DI or mb smth else
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
        this.algorithmList.setListData(RenameAlgorithmType.values());
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
            
            var files = Arrays.asList(fileChooser.getSelectedFiles());
            renameController.getSelectedFiles().addAll(files);
            applicationData.setRecentDirectory(fileChooser.getCurrentDirectory().getPath());
            selectedFilesInfoField.setText("[%s] files selected.".formatted(files.size()));

            renameFilesButton.setEnabled(true);
            LOGGER.info("Selected ({}) files to rename.", files.size());
        });

        renameFilesButton.addActionListener(e -> {
            // Opens configuration window for the algorithm selected
            RenameAlgorithmType selectedType = (RenameAlgorithmType) algorithmList.getSelectedValue();
            RenameRobertView configurationView = getConfigurationView(selectedType);
            configurationView.open();
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                applicationData.save();
            }
        });
    }

    private RenameRobertView getConfigurationView(RenameAlgorithmType type) {
        return switch (type) {
            case MEDAL_READABLE_DATE -> new MedalReadableDateDetailsView(applicationData, renameController);
            case ENUMERATION -> throw new NotImplementedException("TODO");
        };
    }


}
