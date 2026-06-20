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
        String input = "MedalTVCatWars20241001221309.mkv";

        String newPrefix = "Cat Wars";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, newPrefix);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("%s 2024-10-01  22-13-09".formatted(newPrefix), result.getNewName());
    }

    @Test
    public void rejectsIncorrectMedalFileNames() {
        String input = "MedalTVABECADLO12024109.mkv";

        String emptyName = "";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, "any");

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("%s".formatted(emptyName), result.getNewName());
        assertFalse(StringUtils.isEmpty(result.getErrorMessage()));
    }

    @Test
    public void allowsNoPrefix(){
        String input = "MedalTVCatWars20241001221309.mkv";

        String emptyName = "";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, emptyName);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("2024-10-01  22-13-09".formatted(emptyName), result.getNewName());
    }

    @Test
    public void skipsDigitsAfterYYYYMMDDHHMMSS(){
        String input = "MedalTVWorldofWarcraftClassic20260619225517832.mp4";

        String newPrefix = "";
        RenameDetails renameDetails = new RenameDetails(RenameAlgorithmType.MEDAL_READABLE_DATE, newPrefix);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        RenameResult result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("2026-06-19  22-55-17".formatted(newPrefix), result.getNewName());
    }

}
