package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType type) {
        super(roomNumber, 0.0, type);
    }

    @Override
    public boolean isFree() {
        return true;
    }
}
