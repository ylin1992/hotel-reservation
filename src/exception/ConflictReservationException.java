package exception;

public class ConflictReservationException extends Exception {
    public ConflictReservationException(String errMessage) {
        super(errMessage);
    }
}
