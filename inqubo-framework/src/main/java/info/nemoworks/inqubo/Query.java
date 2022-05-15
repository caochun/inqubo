package info.nemoworks.inqubo;

import lombok.Getter;
import lombok.NonNull;

public class Query<T extends Aggregate> {

    @Getter
    private final T source;

    public Query(@NonNull T source) {
        this.source = source;
    }
}