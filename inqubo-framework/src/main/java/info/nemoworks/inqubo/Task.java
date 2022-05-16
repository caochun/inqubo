package info.nemoworks.inqubo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task<T extends Aggregate> {

    private String subject;
    private Query<? extends T> query;
    private Command<? extends T> command;
    private T object;

}
