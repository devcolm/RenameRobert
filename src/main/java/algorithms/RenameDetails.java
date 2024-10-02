package algorithms;

import lombok.Getter;

@Getter
public class RenameDetails {

    private final String prefix;

    public RenameDetails(String prefix) {
        this.prefix = prefix;
    }
}
