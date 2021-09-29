package service;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    // customer and reservation details
    private HashMap<Customer, List<Reservation>> reservations;
    // Room and reserved period
    private HashMap<IRoom, List<Date[]>> roomTable;

    private static ReservationService instance = null;

    public static ReservationService getInstance() {
        if (instance == null) {
            return new ReservationService();
        }
        return instance;
    }

    private ReservationService() {
        reservations = new HashMap<>();
        roomTable = new HashMap<>();
    }

    public void addRoom(IRoom room) {
        if (!roomTable.containsKey(room)) {
            roomTable.put(room, new ArrayList<>());
        } else {
            throw new IllegalArgumentException("Duplicate room id");
        }

    }

    public IRoom getARoom(String roomId) {
        for (IRoom room : roomTable.keySet()) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        throw new IllegalArgumentException("No room matched");
    }

    private boolean isValidRequest(IRoom room, Date checkInDate, Date checkOutDate) {
        if (!roomTable.containsKey(room)) {
            return false;
        }
        if (roomTable.get(room).size() == 0) {
            return true;
        }
        for (Date[] dates : roomTable.get(room)) {
            if ((checkInDate.compareTo(dates[0]) <= 0 && checkOutDate.compareTo(dates[1]) >= 0)
                    || (checkInDate.compareTo(dates[0]) >= 0 && checkInDate.compareTo(dates[1]) <= 0)
                    || (checkOutDate.compareTo(dates[0]) >= 0 && checkOutDate.compareTo(dates[1]) <= 0)
            ) {
                return false;
            }
        }
        return true;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        if (isValidRequest(room, checkInDate, checkOutDate)) {
            Reservation reservation = new Reservation(customer, checkInDate, checkOutDate, room);
            if (!reservations.containsKey(customer)) {
                reservations.put(customer, new ArrayList<>());
            }
            reservations.get(customer).add(reservation);
            roomTable.get(room).add(new Date[]{checkInDate, checkOutDate});
            return reservation;
        } else {
            throw new IllegalArgumentException("Invalid request, please check your room number or dates");
        }
    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate.compareTo(checkOutDate) >= 0) {
            throw new IllegalArgumentException("Invalid dates");
        }
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : roomTable.keySet()) {
            List<Date[]> list = roomTable.get(room);
            if (list.size() == 0) {
                availableRooms.add(room);
            } else {
                for (Date[] dates : roomTable.get(room)) {
                    if (isValidRequest(room, dates[0], dates[1])) {
                        availableRooms.add(room);
                        break;
                    }
                }
            }
        }
        return availableRooms;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer) {
        if (reservations.containsKey(customer)) {
            return reservations.get(customer);
        } else {
            throw new IllegalArgumentException("No customer reservations found");
        }
    }

    public void printAllReservation() {
        for (List<Reservation> reservationList : reservations.values()) {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation);
            }
        }
    }

    @SuppressFBWarnings("HE_SIGNATURE_DECLARES_HASHING_OF_UNHASHABLE_CLASS")
    public void displayAllRooms() {
        for (IRoom room : roomTable.keySet()) {
            System.out.println(room);
        }
    }

}
