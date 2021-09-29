package exception;

public class InvalidDateException extends Exception {
    public InvalidDateException(String errMessage) {
        super(errMessage);
    }
}
