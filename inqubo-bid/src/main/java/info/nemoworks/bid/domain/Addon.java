package info.nemoworks.bid.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Addon {

    private final Instant timestamp = Instant.now();

    @NonNull
    private String bidId;

    @NonNull
    private String message;

    private String author;

}
