package core;

import algorithms.MedalReadableDateAlgorithm;
import algorithms.RenameAlgorithm;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Setter
public class RenameController {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final RenameAlgorithm DEFAULT_ALGORITHM = new MedalReadableDateAlgorithm();

    private RenameAlgorithm selectedAlgorithm = DEFAULT_ALGORITHM;
    private File[] selectedFiles;

    public void execute() {
        LOGGER.info("BAM!");
    }

}
