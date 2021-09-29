package api;

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
        throw new UnsupportedOperationException();
    }

    public void createACustomer(String email, String firstName, String lastName) {
        try {
            customerService.addCustomer(email, firstName, lastName);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
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
