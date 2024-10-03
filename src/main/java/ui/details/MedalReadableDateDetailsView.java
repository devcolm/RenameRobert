package ui.details;

import algorithms.RenameDetails;
import core.ApplicationData;
import core.RenameController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.RenameRobertView;

import javax.swing.*;
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
            }
            RenameDetails renameDetails = new RenameDetails(prefix);
            var errors = renameController.execute(renameDetails);

            int resultIcon = errors.getErrors().size() == 0 ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(this,
                    "Operation completed with (%s) issues.".formatted(errors.getErrors().size()),
                    "Rename Robert",
                    resultIcon);
        });
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
