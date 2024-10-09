import algorithms.MedalReadableDateAlgorithm;
import algorithms.RenameAlgorithmType;
import algorithms.RenameDetails;
import algorithms.RenameResult;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MedalReadableDateAlgorithmTest {

    @Test
    public void makesReadable() {
        String input = "MedalTVCatWars20241001221309";

        String newPrefix = "Cat Wars";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, newPrefix);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("%s 2024-10-01  22-13-09".formatted(newPrefix), result.getNewName());
    }

    @Test
    public void rejectsIncorrectMedalFileNames() {
        String input = "MedalTVABECADLO120241001221309";

        String emptyName = "";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, emptyName);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("%s".formatted(emptyName), result.getNewName());
        assertFalse(StringUtils.isEmpty(result.getErrorMessage()));
    }
}
