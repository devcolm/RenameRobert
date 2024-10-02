import ui.RenameRobertUI;

public class RenameRobert {

    public static void main(String[] args) {
        configureSystemOptions();

        var applicationWindow = new RenameRobertUI();
        applicationWindow.initialise();
    }

    private static void configureSystemOptions() {
        // Fixes scaling issue
        // @ https://github.com/kirill-grouchnikov/substance/issues/48
        System.setProperty("sun.java2d.uiScale", "1.0");
    }

}
