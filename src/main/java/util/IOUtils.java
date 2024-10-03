package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class IOUtils {

    private static final Logger LOGGER = LogManager.getLogger();

    public Yaml getYaml() {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        return new Yaml(options);
    }

    public BufferedImage loadImage(String relativePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(relativePath);

        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (Exception e) {
            LOGGER.error("Error loading image: ", e);
        }

        return image;
    }
}
