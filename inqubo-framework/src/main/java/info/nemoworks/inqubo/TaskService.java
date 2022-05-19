package info.nemoworks.inqubo;

public class TaskService {

    public <T extends Aggregate> void handleCommand(Command<T> command) {

    }

    public <T extends Aggregate> T handleQuery(Query<T> query) {
        return null;
    }
}
