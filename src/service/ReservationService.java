package service;

import exception.InvalidDateException;
import exception.NoRoomFoundException;
import model.Customer;
import model.IRoom;
import model.Reservation;
import utils.DateHelper;

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

    boolean isValidRequest(IRoom room, Date checkInDate, Date checkOutDate) {
        if (roomTable.get(room).size() == 0) {
            return true;
        }
        for (Date[] dates : roomTable.get(room)) {
            if ((checkInDate.compareTo(dates[0]) <= 0 && checkOutDate.compareTo(dates[1]) >= 0)
                    || (checkInDate.compareTo(dates[0]) >= 0 && checkInDate.compareTo(dates[1]) < 0)
                    || (checkOutDate.compareTo(dates[0]) > 0 && checkOutDate.compareTo(dates[1]) <= 0)
            ) {
                return false;
            }
        }
        return true;
    }

    boolean isValidDate(Date checkInDate, Date checkOutDate) {
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

    private HashMap<IRoom, Date[]> findRoomsHelper(Date checkInDate, Date checkOutDate) {
        HashMap<IRoom, Date[]> map = new HashMap<>();
        int offset = 0;
        while (map.size() == 0) {
            for (IRoom room : roomTable.keySet()) {
                Date offsetCheckInDate = DateHelper.addDate(checkInDate, offset);
                Date offsetCheckOutDate = DateHelper.addDate(checkOutDate, offset);
                if (isValidRequest(room, offsetCheckInDate, offsetCheckOutDate)) {
                    room.setAvailableDates(new Date[]{offsetCheckInDate, offsetCheckOutDate});
                    map.put(room, new Date[]{offsetCheckInDate, offsetCheckOutDate});
                }
            }
            if (map.size() == 0 && offset == 0) {
                System.out.println("No available room found in this period, searching for recommended date...");
            }
            offset += 7;
        }
        return map;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) throws InvalidDateException {
        if (!isValidDate(checkInDate, checkOutDate)) {
            throw new InvalidDateException("");
        }
        List<IRoom> availableRooms = new ArrayList<>();
        HashMap<IRoom, Date[]> map = findRoomsHelper(checkInDate, checkOutDate);
        for (Map.Entry<IRoom, Date[]> entry : map.entrySet()) {
            Date[] dates = entry.getValue();
            IRoom room = entry.getKey();
            availableRooms.add(room);
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
