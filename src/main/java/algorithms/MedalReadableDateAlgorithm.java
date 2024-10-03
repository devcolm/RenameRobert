package algorithms;

public class MedalReadableDateAlgorithm implements RenameAlgorithm {

    // TODO: add precaution if the application medal records has digits in its name as we're currently
    // unsure if medal keeps them for its default file name or not
    private static final String NON_DIGITS = "\\D";

    @Override
    public String rename(String input, RenameDetails renameDetails) {
        String dateString = input.replaceAll(NON_DIGITS, ""); //  20241001221309

        return "%s %s".formatted(renameDetails.getPrefix(), toReadableFormat(dateString));
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
