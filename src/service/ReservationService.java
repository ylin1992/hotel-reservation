package service;

import exception.InvalidDateException;
import exception.NoRoomFoundException;
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
            instance = new ReservationService();
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
        return null;
    }

    private boolean isValidRequest(IRoom room, Date checkInDate, Date checkOutDate) {
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

    private boolean isValidDate(Date checkInDate, Date checkOutDate) {
        return checkInDate.compareTo(checkOutDate) < 0;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) throws InvalidDateException, NoRoomFoundException {
        if (!isValidDate(checkInDate, checkOutDate)) {
            throw new InvalidDateException("Invalid date, please check your input again");
        }

        if (isValidRequest(room, checkInDate, checkOutDate)) {
            Reservation reservation = new Reservation(customer, checkInDate, checkOutDate, room);
            if (!reservations.containsKey(customer)) {
                reservations.put(customer, new ArrayList<>());
            }
            reservations.get(customer).add(reservation);
            roomTable.get(room).add(new Date[]{checkInDate, checkOutDate});
            return reservation;
        } else {
            throw new NoRoomFoundException("No room was found");
        }
    }


    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) throws InvalidDateException, NoRoomFoundException {
        if (!isValidDate(checkInDate, checkOutDate)) {
            throw new InvalidDateException("");
        }
        List<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : roomTable.keySet()) {
            List<Date[]> list = roomTable.get(room);
            if (list.size() == 0) {
                availableRooms.add(room);
            } else {
                for (Date[] dates : roomTable.get(room)) {
                    if (isValidRequest(room, checkInDate, checkOutDate)) {
                        availableRooms.add(room);
                        break;
                    }
                }
            }
        }
        if (availableRooms.size() == 0) {
            throw new NoRoomFoundException("No room within the time period is found");
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
        if (reservations.size() == 0) {
            System.out.println("No reservation has been created.");
        }
        for (List<Reservation> reservationList : reservations.values()) {
            for (Reservation reservation : reservationList) {
                System.out.println(reservation);
            }
        }
    }

    public List<IRoom> getAllRooms() {
        List<IRoom> rooms = new ArrayList<>();
        for (IRoom room : roomTable.keySet()) {
            rooms.add(room);
        }
        return rooms;
    }

}
