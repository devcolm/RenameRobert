package algorithms;

import java.util.regex.Pattern;

public class MedalReadableDateAlgorithm implements RenameAlgorithm {

    private static final Pattern MEDAL_PATTERN = Pattern.compile("MedalTV[a-zA-z]+\\d{14}.\\w+");
    private static final String WORD_CHARACTERS = "[a-zA-z]";

    @Override
    public RenameResult rename(String input, RenameDetails renameDetails) {
        RenameResult renameResult = new RenameResult();

        if (!MEDAL_PATTERN.matcher(input).matches()) {
            renameResult.setNewName("");
            renameResult.setErrorMessage("Name '%s' does not match the default Medal naming pattern.".formatted(input));
            return renameResult;
        }

        String dateString = input.replaceAll(WORD_CHARACTERS, ""); //  20241001221309
        renameResult.setNewName("%s %s".formatted(renameDetails.getPrefix(), toReadableFormat(dateString)));
        return renameResult;
    }

    private String toReadableFormat(String dateString) {
        String year = dateString.substring(0, 4);
        String month = dateString.substring(4, 6);
        String day = dateString.substring(6, 8);

        String hour = dateString.substring(8, 10);
        String minute = dateString.substring(10, 12);
        String second = dateString.substring(12, 14);

        return "%s-%s-%s  %s-%s-%s".formatted(year, month, day, hour, minute, second);
    }

}
