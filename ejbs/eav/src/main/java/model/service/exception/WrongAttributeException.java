package model.service.exception;

public class WrongAttributeException extends Throwable {
    public WrongAttributeException(String message) {
        super(message);
    }

    public WrongAttributeException() {
        super();
    }
}
