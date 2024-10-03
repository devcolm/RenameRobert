package ui.details;

import core.ApplicationData;
import ui.RenameRobertView;

import javax.swing.*;

public class MedalReadableDateDetails extends RenameRobertView {
    private JPanel contentPane;
    private JButton OKButton;
    private JTextField textField1;

    public MedalReadableDateDetails(ApplicationData applicationData) {
        super(applicationData);
    }

    @Override
    protected void initialise() {
        setWindowDefaults(contentPane);

        this.setTitle(ApplicationData.APPLICATION_NAME);
        this.setSize(420, 500);
    }

    @Override
    protected void configureAppearance() {

    }

    @Override
    protected void configureActions() {

    }
}
