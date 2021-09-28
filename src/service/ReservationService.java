package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class ReservationService {

    HashMap<Customer, Reservation> reservations;

    public void addRoom(IRoom room) {

    }

    public IRoom getARoom(String roomId) {
        throw new UnsupportedOperationException();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        throw new UnsupportedOperationException();
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        throw new UnsupportedOperationException();
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        throw new UnsupportedOperationException();
    }

    public void printAllReservation() {

    }

}
