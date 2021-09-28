package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType type) {
        super(roomNumber, 0.0, type);
    }

    @Override
    public String toString() {
        String out = "\n==================";
        out += "Room number: " + roomNumber;
        out += "Price: Free";
        out += "Room type: " + enumeration.toString();
        out = "\n==================";
        return out;
    }
}
