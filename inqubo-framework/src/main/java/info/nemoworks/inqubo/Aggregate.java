package info.nemoworks.inqubo;

import lombok.Getter;

import java.util.UUID;

public class Aggregate {

    @Getter
    private final String id = UUID.randomUUID().toString();
}
