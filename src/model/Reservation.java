package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    Date checkInDate;
    Date checkOutDate;
    IRoom room;

    public Reservation(Customer customer, Date checkInDate, Date checkOutDate, IRoom room) {
        if (checkInDate.after(checkOutDate) || checkInDate.equals(checkOutDate)) {
            throw new IllegalArgumentException("Invalid check in date and check out date, checkin >= checkout");
        }
        this.customer = customer;
        this.checkOutDate = checkOutDate;
        this.checkInDate = checkInDate;
        this.room = room;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o.getClass() == this.getClass())) {
            return false;
        }
        Reservation reservation = (Reservation) o;
        return reservation.customer.equals(this.customer)
                && reservation.checkInDate.equals(this.checkInDate)
                && reservation.checkOutDate.equals(this.checkOutDate);
    }

}
