package exception;

public class UndefinedActionException extends Exception {
    public UndefinedActionException(String errMessage) {
        super(errMessage);
    }
}
