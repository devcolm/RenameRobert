package core;

import algorithms.RenameAlgorithm;
import algorithms.RenameAlgorithmFactory;
import algorithms.RenameDetails;
import com.google.common.collect.Lists;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;

@Setter
public class RenameController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final RenameAlgorithmFactory renameAlgorithmFactory;
    private Collection<File> selectedFiles;

    public RenameController(RenameAlgorithmFactory renameAlgorithmFactory){
        this.renameAlgorithmFactory = renameAlgorithmFactory;
    }

    public RenameResult execute(RenameDetails renameDetails) {
        Collection<String> errors = Lists.newLinkedList();
        for (File file : selectedFiles) {
            RenameAlgorithm selectedAlgorithm = renameAlgorithmFactory.get(renameDetails.getType());

            String newName = file.getParent() + File.separator + selectedAlgorithm.rename(file.getName(), renameDetails)
                    + "." + FilenameUtils.getExtension(file.getName());

            if (!file.renameTo(new File(newName))) {
                String errorMessage = "Error renaming '%s' to '%s'".formatted(file.getName(), newName);
                errors.add(errorMessage);
                LOGGER.error(errorMessage);
            }
        }
        return new RenameResult(errors);
    }

}
