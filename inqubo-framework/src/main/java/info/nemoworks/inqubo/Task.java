package info.nemoworks.inqubo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task<T extends Aggregate> {

    enum STATUS {
        PENDING, ACCEPTED, COMPLETED
    }

    private String subject;
    private Query<? extends T> query;
    private Command<? extends T> command = null;
    private T object;

    private AbstractProcess<T> process;

    private STATUS status = STATUS.PENDING;

    public TaskDelegate accept() {
        this.status = STATUS.ACCEPTED;
        process.taskStatusChanged(this);
        return new TaskDelegate(this);
    }

    public void complete(Command<? extends T> command) {
        this.status = STATUS.COMPLETED;
        this.command = command;
        process.taskStatusChanged(this);
    }

}
