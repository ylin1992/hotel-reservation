package driver;

import model.*;
import service.CustomerService;
import service.ReservationService;
import utils.DateHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DriverHelper {
    public static Date[] getDates(int[] date1, int[] date2) {
        Calendar calendar = Calendar.getInstance();
        Date[] out = new Date[2];

        calendar.set(date1[0], date1[1], date1[2]);
        out[0] = calendar.getTime();
        calendar.set(date2[0], date2[1], date2[2]);
        out[1] = calendar.getTime();

        return out;
    }

    public static ReservationService initializeReservationService() {
        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();
        //ReservationService reservationService = new ReservationService();
        reservationService.addRoom(new FreeRoom("101", RoomType.SINGLE));
        reservationService.addRoom(new FreeRoom("102", RoomType.DOUBLE));
        reservationService.addRoom(new FreeRoom("103", RoomType.SINGLE));
        //reservationService.addRoom(new FreeRoom("104", RoomType.DOUBLE));
        //reservationService.addRoom(new FreeRoom("105", RoomType.SINGLE));
        //reservationService.addRoom(new FreeRoom("106", RoomType.DOUBLE));
        //reservationService.addRoom(new Room("107", 20.8, RoomType.SINGLE));
        //reservationService.addRoom(new Room("108", 30.8, RoomType.DOUBLE));
        //reservationService.addRoom(new Room("109", 40.8, RoomType.DOUBLE));

        // add some reservation
        IRoom room = reservationService.getARoom("101");
        Date checkIn = DateHelper.getDateFromInt(2021, 11, 11);
        Date checkOut = DateHelper.getDateFromInt(2021, 11, 17);
        Customer customer = customerService.getCustomer("leo@gmail.com");

        IRoom room2 = reservationService.getARoom("102");
        Date checkIn2 = DateHelper.getDateFromInt(2021, 11, 11);
        Date checkOut2 = DateHelper.getDateFromInt(2021, 11, 17);
        Customer customer2 = customerService.getCustomer("leo@gmail.com");

        IRoom room3 = reservationService.getARoom("103");
        Date checkIn3 = DateHelper.getDateFromInt(2021, 11, 11);
        Date checkOut3 = DateHelper.getDateFromInt(2021, 11, 17);
        Customer customer3 = customerService.getCustomer("leo@gmail.com");
        try {
            reservationService.reserveARoom(customer, room, checkIn, checkOut);
            reservationService.reserveARoom(customer2, room2, checkIn2, checkOut2);
            reservationService.reserveARoom(customer3, room3, checkIn3, checkOut3);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return reservationService;
    }

    public static void initializeCustomerService() {
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer("Leo@gmail.com", "K", "Wang");
        customerService.addCustomer("Wang@gmail.com", "WW", "Tseng");
        customerService.addCustomer("Den@gmail.com", "CC", "Lin");
        customerService.addCustomer("QQQ@gmail.com", "EE", "Amy");
        customerService.addCustomer("CCC@gmail.com", "VV", "Len");
        customerService.addCustomer("DDD@gmail.com", "CC", "Mung");
        customerService.addCustomer("WWWW@gmail.com", "EF", "Wen");
    }

    public static List<IRoom> getRoomList(boolean isNull, boolean isEmpty) {
        if (isNull) {
            return null;
        }
        List<IRoom> rooms = new ArrayList<>();
        if (isEmpty) {
            return rooms;
        }

        rooms.add(new FreeRoom("101", RoomType.SINGLE));
        rooms.add(new FreeRoom("102", RoomType.DOUBLE));
        rooms.add(new FreeRoom("103", RoomType.SINGLE));
        rooms.add(new FreeRoom("104", RoomType.DOUBLE));
        rooms.add(new FreeRoom("105", RoomType.SINGLE));
        rooms.add(new FreeRoom("106", RoomType.DOUBLE));
        rooms.add(new Room("107", 20.8, RoomType.SINGLE));
        rooms.add(new Room("108", 30.8, RoomType.DOUBLE));
        rooms.add(new Room("109", 40.8, RoomType.DOUBLE));

        return rooms;
    }

}
