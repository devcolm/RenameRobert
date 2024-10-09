package algorithms;

import java.util.Map;
import java.util.function.Supplier;

public class RenameAlgorithmFactory {

    private static final Map<RenameAlgorithmType, Supplier<RenameAlgorithm>> ALGORITHM_MAP = Map.of(
            RenameAlgorithmType.MEDAL_READABLE_DATE, MedalReadableDateAlgorithm::new,
            RenameAlgorithmType.ENUMERATION, EnumerationAlgorithm::new
    );

    public RenameAlgorithm get(RenameAlgorithmType type) {
        return ALGORITHM_MAP.get(type).get();
    }

}
