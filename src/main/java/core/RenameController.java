package core;

import algorithms.RenameAlgorithm;
import algorithms.RenameAlgorithmFactory;
import algorithms.RenameDetails;
import algorithms.RenameResult;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Collection;

@Setter
public class RenameController {

    private static final Logger LOGGER = LogManager.getLogger();

    private final RenameAlgorithmFactory renameAlgorithmFactory;
    @Getter
    private final Collection<File> selectedFiles;

    public RenameController(RenameAlgorithmFactory renameAlgorithmFactory) {
        this.renameAlgorithmFactory = renameAlgorithmFactory;
        this.selectedFiles = Lists.newLinkedList();
    }

    public Collection<RenameResult> execute(RenameDetails renameDetails) {
        Collection<RenameResult> renameResults = Lists.newLinkedList();
        for (File file : selectedFiles) {
            RenameAlgorithm selectedAlgorithm = renameAlgorithmFactory.get(renameDetails.getType());

            RenameResult renameResult = selectedAlgorithm.rename(file.getName(), renameDetails);
            String newName = file.getParent() + File.separator + renameResult.getNewName()
                    + "." + FilenameUtils.getExtension(file.getName());

            if (StringUtils.isEmpty(newName) || !file.renameTo(new File(newName))) {
                String errorMessage = "Error renaming '%s' to '%s'. %s".formatted(file.getName(), newName, renameResult.getErrorMessage());
                renameResult.setErrorMessage(errorMessage);
                renameResults.add(renameResult);
                LOGGER.error(errorMessage);
            }
        }
        return renameResults;
    }

}
