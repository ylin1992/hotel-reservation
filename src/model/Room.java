package model;

public class Room implements IRoom {

    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType type) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = type;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Double getRoomPrice() {
        return price;
    }

    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        String out = "\n==================";
        out += "Room number: " + roomNumber;
        out += "Price: $" + price.toString() + " per night";
        out += "Room type: " + enumeration.toString();
        out = "\n==================";
        return out;
    }
}
