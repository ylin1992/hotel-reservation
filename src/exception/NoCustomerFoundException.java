package exception;

public class NoCustomerFoundException extends Exception {
    public NoCustomerFoundException(String errMessage) {
        super(errMessage);
    }
}
