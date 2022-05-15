package info.nemoworks.inqubo;

import lombok.Data;

import java.util.UUID;

@Data
public class Aggregate {

    private final String id = UUID.randomUUID().toString();
}
