package apptive.basic.exception;

public class NotFoundToDo extends RuntimeException {
    public NotFoundToDo(String message) {
        super(message);
    }
}
