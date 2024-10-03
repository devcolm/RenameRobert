package ui;

import core.ApplicationData;

import javax.swing.*;

public abstract class RenameRobertView extends JFrame {

    protected ApplicationData applicationData;

    public RenameRobertView(ApplicationData applicationData) {
        this.applicationData = applicationData;
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
