import algorithms.MedalReadableDateAlgorithm;
import algorithms.RenameDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedalReadableDateAlgorithmTest {

    @Test
    public void makesReadable() {
        String input = "MedalTVCatWars20241001221309";

        String newPrefix = "Cat Wars";
        RenameDetails renameDetails = new RenameDetails(newPrefix);

        MedalReadableDateAlgorithm medalReadableDateAlgorithm = new MedalReadableDateAlgorithm();
        String result = medalReadableDateAlgorithm.rename(input, renameDetails);

        assertEquals("%s 2024-10-01  22-13-09".formatted(newPrefix), result);
    }
}
