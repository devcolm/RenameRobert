package core;

import lombok.Getter;

import java.util.Collection;

@Getter
public class RenameResult {

    private final Collection<String> errors;

    public RenameResult(Collection<String> errors) {
        this.errors = errors;
    }
}
