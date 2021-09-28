import model.Customer;
import model.FreeRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;

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
        ReservationService reservationService = new ReservationService();
        reservationService.addRoom(new FreeRoom("101", RoomType.SINGLE));
        reservationService.addRoom(new FreeRoom("102", RoomType.DOUBLE));
        reservationService.addRoom(new FreeRoom("103", RoomType.SINGLE));
        reservationService.addRoom(new FreeRoom("104", RoomType.DOUBLE));
        reservationService.addRoom(new FreeRoom("105", RoomType.SINGLE));
        reservationService.addRoom(new FreeRoom("106", RoomType.DOUBLE));
        reservationService.addRoom(new Room("107", 20.8, RoomType.SINGLE));
        reservationService.addRoom(new Room("108", 30.8, RoomType.DOUBLE));
        reservationService.addRoom(new Room("109", 40.8, RoomType.DOUBLE));
        return reservationService;
    }

    public static List<Customer> initializeCustomerList() {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("Leo@gmail.com", "WWW", "Wang"));
        list.add(new Customer("Wang@gmail.com", "WW", "Wang"));
        list.add(new Customer("Den@gmail.com", "CC", "Wang"));
        list.add(new Customer("QQQ@gmail.com", "EE", "Wang"));
        list.add(new Customer("CCC@gmail.com", "VV", "Wang"));
        list.add(new Customer("DDD@gmail.com", "CC", "Wang"));
        list.add(new Customer("WWWW@gmail.com", "EF", "Wang"));
        return list;
    }
}
