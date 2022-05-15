package info.nemoworks.inqubo;

import lombok.Getter;
import lombok.NonNull;

public abstract class Command<T extends Aggregate> {

    @Getter
    private final T target;

    public Command(@NonNull T target) {
        this.target = target;
    }
}
