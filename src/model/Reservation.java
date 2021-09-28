package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    Date checkInDate;
    Date checkOutDate;
    IRoom room;

    @Override
    public String toString() {
        String out = "\n==================\n";
        out += "Customer: " + customer.getName() + "\n";
        out += "Email: " + customer.getEmail() + "\n";
        out += "Check in date: " + checkInDate.toString() + "\n";
        out += "Check out date:" + checkOutDate.toString() + "\n";
        out += "==================\n";
        return out;
    }
}
