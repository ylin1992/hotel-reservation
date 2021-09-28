package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public Customer getCustomer(String email) {
        throw new UnsupportedOperationException();
    }

    public void createACustomer(String email, String firstName, String lastName) {
        throw new UnsupportedOperationException();
    }

    public IRoom getRoom(String roomNumber) {
        throw new UnsupportedOperationException();
    }

    public Reservation bookARoom(String customerEmail, Date checkInDate, Date checkOutDate) {
        throw new UnsupportedOperationException();
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        throw new UnsupportedOperationException();
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        throw new UnsupportedOperationException();
    }
}
