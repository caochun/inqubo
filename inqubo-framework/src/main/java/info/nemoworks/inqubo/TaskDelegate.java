package info.nemoworks.inqubo;

public class TaskDelegate<T extends Aggregate> {

    private Task<? extends  T> task;

    public TaskDelegate(Task<? extends T> task){
        this.task = task;
    }

    private AbstractService<? extends T> service;

    public TaskView getView(){
        return new TaskView();
    }
}
