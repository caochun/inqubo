package info.nemoworks.inqubo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Task<T extends Aggregate> {

    enum STATUS {
        PENDING, ACCEPTED, COMPLETED
    }

    private String subject;
    private Query<? extends T> query;

    private List<Command<? extends T>> expectedCommands = null;

    private Command<? extends T> completingCommand = null;

    private T object;

    private AbstractProcess<T> process;

    private STATUS status = STATUS.PENDING;

    public boolean accept() {
        if (this.status != STATUS.PENDING) return false;
        this.status = STATUS.ACCEPTED;
        process.taskStatusChanged(this);
        return true;
    }

    public boolean complete(Command<? extends T> command) {
        if (!this.expectedCommands.contains(command)) return false;
        this.status = STATUS.COMPLETED;
        this.completingCommand = command;
        return process.taskStatusChanged(this);
    }

}
