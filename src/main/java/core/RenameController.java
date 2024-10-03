package core;

import algorithms.MedalReadableDateAlgorithm;
import algorithms.RenameAlgorithm;
import algorithms.RenameDetails;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;

@Setter
public class RenameController {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final RenameAlgorithm DEFAULT_ALGORITHM = new MedalReadableDateAlgorithm();

    private RenameAlgorithm selectedAlgorithm = DEFAULT_ALGORITHM;
    // TODO: this should be specified by the user and set after 'rename' is clicked => execute after renamedetails are set
    private RenameDetails renameDetails;

    private File[] selectedFiles;

    public RenameResult execute() {
        Collection<String> errors = Lists.newLinkedList();

        for (File file : selectedFiles) {
            String newName = selectedAlgorithm.rename(file.getName(), renameDetails);
            if (!file.renameTo(new File(newName))) {
                String errorMessage = "Error renaming '%s' to '%s'".formatted(file.getName(), newName);
                errors.add(errorMessage);
                LOGGER.error(errorMessage);
            }
        }
        return new RenameResult(errors);
    }

}
