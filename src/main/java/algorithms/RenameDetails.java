package algorithms;

import lombok.Getter;

@Getter
public class RenameDetails {

    private final RenameAlgorithmType type;
    private final String prefix;

    public RenameDetails(RenameAlgorithmType type, String prefix) {
        this.type = type;
        this.prefix = prefix;
    }
}
