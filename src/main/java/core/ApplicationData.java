package core;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import util.IOUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

@Getter
@Setter
public class ApplicationData {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String DEFAULT_BROWSE_DIRECTORY = ".";
    public static final String APPLICATION_NAME = "Rename Robert";

    private final BufferedImage image;

    private final File settingsFile;
    private final Map<String, Object> applicationSettings;

    private String recentDirectory;

    public ApplicationData() {
        this.settingsFile = new File(getUserDataDirectory() + File.separator + "settings.yaml");
        this.applicationSettings = loadSettings();
        this.image = new IOUtils().loadImage("icons/RenameRobert.jpg");

        this.recentDirectory = (String) applicationSettings.get(ApplicationProperties.RECENT_DIRECTORY);
    }

    public void save() {
        applicationSettings.put(ApplicationProperties.RECENT_DIRECTORY, recentDirectory);

        LOGGER.info("Saving application settings.");
        persistSettings(applicationSettings);
    }

    public String getUserDataDirectory() {
        return System.getProperty("user.home") + File.separator + ".rrobert";
    }

    public String getTestDirectory() {
        return getUserDataDirectory() + File.separator + "test";
    }

    private Map<String, Object> loadSettings() {
        Yaml yaml = new IOUtils().getYaml();

        File appFolder = new File(getUserDataDirectory());
        if (!appFolder.exists()) {
            LOGGER.info("Initialising application settings folder in {}.", appFolder.getAbsolutePath());
            if (!appFolder.mkdirs()) {
                LOGGER.error("Error creating application settings folder!");
            }
            File testFolder = new File(getTestDirectory());
            if (!testFolder.mkdirs()) {
                LOGGER.error("Error creating application settings test folder!");
            }
        }

        if (!settingsFile.exists()) {
            LOGGER.info("Initialising application settings.");
            try {
                if (!settingsFile.createNewFile()) {
                    LOGGER.error("Error creating settings file!.");
                }

                Map<String, Object> defaultSettings = yaml.load(getClass().getClassLoader()
                        .getResourceAsStream("settings/settings.yaml"));

                persistSettings(defaultSettings);
            } catch (IOException e) {
                LOGGER.error("Error creating settings file.");
                throw new RuntimeException(e);
            }
        }

        Map<String, Object> settings = Collections.emptyMap();
        try (FileInputStream stream = new FileInputStream(settingsFile)) {
            settings = yaml.load(stream);
        } catch (IOException e) {
            LOGGER.error("Error loading file {}", settingsFile, e);
        }

        return settings;
    }

    private void persistSettings(Map<String, Object> settingsMap) {
        try {
            new IOUtils().getYaml().dump(settingsMap, new PrintWriter(settingsFile));
        } catch (FileNotFoundException e) {
            LOGGER.error("Error while trying to persist settings: ", e);
        }
    }


}
