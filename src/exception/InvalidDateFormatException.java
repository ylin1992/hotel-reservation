package exception;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException(String errMessage) {
        super(errMessage);
    }
}
