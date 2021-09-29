package exception;

public class UnregisteredRoomException extends Exception {
    public UnregisteredRoomException(String errMessage) {
        super(errMessage);
    }
}
