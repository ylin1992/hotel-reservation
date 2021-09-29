package api;

import exception.UnregisteredRoomException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static HotelResource instance = null;
    private CustomerService customerService;
    private ReservationService reservationService;

    private HotelResource() {
        customerService = CustomerService.getInstance();
        reservationService = ReservationService.getInstance();
    }

    public static HotelResource getInstance() {
        if (instance == null) {
            instance = new HotelResource();
        }
        return instance;
    }

    public Customer getCustomer(String email) {
        try {
            return customerService.getCustomer(email);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public void createACustomer(String email, String firstName, String lastName) {
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public IRoom getRoom(String roomNumber) throws UnregisteredRoomException {
        try {
            return reservationService.getARoom(roomNumber);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        try {
            Customer customer = customerService.getCustomer(customerEmail);
            return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        try {
            Customer customer = customerService.getCustomer(customerEmail);
            return reservationService.getCustomerReservation(customer);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        try {
            return reservationService.findRooms(checkInDate, checkOutDate);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return null;
    }

    public void displayRooms(Collection<IRoom> rooms) {
        for (IRoom room : rooms) {
            System.out.println(room);
        }
    }
}
