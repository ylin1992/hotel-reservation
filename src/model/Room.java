package model;

public class Room implements IRoom {

    private final String roomNumber;
    private final Double price;
    private final RoomType enumeration;

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
        String out = "\n==================\n";
        out += "Room number: " + roomNumber + "\n";
        out += "Price: " + (isFree() ? "Free\n" : "$" + price.toString() + " per night\n");
        out += "Room type: " + enumeration.toString() + "\n";
        out += "==================";
        return out;
    }

    /**
     * @param o o: the room want to compare to
     * @return 1. if the two IRoom instances have an identical address, return true
     * 2. if class names of the two IRoom objects are different, return false
     * 3. if Room number is identical, return true
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!o.getClass().equals(this.getClass())) {
            return false;
        }
        IRoom room = (IRoom) o;
        return room.getRoomNumber().equals(this.roomNumber);
    }

    /*@Override
    public int hashCode() {
        return roomNumber.hashCode();
    }*/
}
