package ui;

import core.ApplicationData;
import core.RenameController;

import javax.swing.*;

public abstract class RenameRobertView extends JFrame {

    protected final ApplicationData applicationData;
    protected final RenameController renameController;

    public RenameRobertView(ApplicationData applicationData, RenameController renameController) {
        this.applicationData = applicationData;
        this.renameController = renameController;
    }

    public void open() {
        initialise();
        configureAppearance();
        configureActions();
    }

    protected abstract void initialise();

    protected abstract void configureAppearance();

    protected abstract void configureActions();

    protected void setWindowDefaults(JPanel contentPane) {
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
