package info.nemoworks.inqubo;

import java.util.List;

public interface AbstractService<T extends Aggregate> {

    public List<Query<? extends T>> getHandledQueries();

    public List<Command<? extends T>> getHandledCommands();
}
