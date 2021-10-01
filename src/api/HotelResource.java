package api;

import exception.InvalidDateException;
import exception.NoRoomFoundException;
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

    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws NoRoomFoundException, InvalidDateException {
        try {
            Customer customer = customerService.getCustomer(customerEmail);
            return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        } catch (Exception ex) {
            throw ex;
        }
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
            return null;
        }

    }

    public void displayRooms(Collection<IRoom> rooms) {
        for (IRoom room : rooms) {
            if (room.getAvailableDates() != null) {
                System.out.println("Date: " + room.getAvailableDates()[0] + " ~ " + room.getAvailableDates()[1]);
            }
            System.out.println(room);
        }
    }

    public void displayReservations(Collection<Reservation> reservations) {
        if (reservations != null && !reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
