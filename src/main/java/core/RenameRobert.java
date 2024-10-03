package core;

import ui.MainView;

public class RenameRobert {

    public static void main(String[] args) {
        configureSystemOptions();

        ApplicationData applicationData = new ApplicationData();
        RenameController renameController = new RenameController();

        var mainView = new MainView(applicationData, renameController);
        mainView.open();
    }

    private static void configureSystemOptions() {
        // Fixes scaling issue
        // @ https://github.com/kirill-grouchnikov/substance/issues/48
        System.setProperty("sun.java2d.uiScale", "1.0");
    }

}
