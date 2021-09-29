package exception;

public class NoRoomFoundException extends Exception {
    public NoRoomFoundException(String errMessage) {
        super(errMessage);
    }
}
