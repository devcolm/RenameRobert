package ui.details;

import algorithms.RenameAlgorithmType;
import algorithms.RenameDetails;
import core.ApplicationData;
import core.RenameController;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.RenameRobertView;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

// TODO: research how to make parent window inactive when the config popups are open
public class MedalReadableDateDetailsView extends RenameRobertView {

    private static final Logger LOGGER = LogManager.getLogger();

    private JPanel contentPane;
    private JButton OKButton;
    private JTextField prefixTextField;

    public MedalReadableDateDetailsView(ApplicationData applicationData, RenameController renameController) {
        super(applicationData, renameController);
    }

    @Override
    protected void initialise() {
        setWindowDefaults(contentPane);

        this.setTitle(ApplicationData.APPLICATION_NAME);
        this.setSize(420, 300);
    }

    @Override
    protected void configureAppearance() {

    }

    @Override
    protected void configureActions() {
        OKButton.addActionListener(e -> {
            String prefix = prefixTextField.getText();
            if (!isValid(prefix)) {
                JOptionPane.showMessageDialog(this,
                        "The requested filename is invalid.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, prefix);
            var renameResuts = renameController.execute(renameDetails);
            long errorCount = renameResuts.stream().filter(result -> !StringUtils.isEmpty(result.getErrorMessage())).count();

            int resultIcon = errorCount == 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(this,
                    "Operation completed with (%s) issues.".formatted(errorCount),
                    "Rename Robert",
                    resultIcon);
            // TODO: list the issues in the window (limit to 5-10 lines)
            cleanup();
        });
    }

    private void cleanup() {
        renameController.getSelectedFiles().clear();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private boolean isValid(String prefix) {
        File file = new File(applicationData.getUserDataDirectory() + File.separator + "test" + File.separator + prefix);

        boolean valid = false;
        try {
            valid = file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("Cannot create file with the following filename: {}", file.getName(), e);
        }

        if (valid) {
            file.delete();
        }

        return valid;
    }
}
