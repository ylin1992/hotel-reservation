package driver;

import api.AdminResource;
import api.HotelResource;
import model.*;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class Driver {

    public static void testModelCustomer() {
        System.out.println("\n Start testing model/Customer \n");
        Customer customer = new Customer("Leo@gmail.com", "Leo", "Wang");
        Customer customerLowerCase = new Customer("leo@gmail.com", "Leo", "Wang");
        System.out.println(customer.hashCode());
        System.out.println(customerLowerCase.hashCode());
        System.out.print(customer);
        try {
            Customer invalidCustomer = new Customer("kkk", "Leo", "Wu");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void testCustomerService() {
        System.out.println("\n Start testing service/CustomerService \n");
        CustomerService customerService = CustomerService.getInstance();
        try {
            customerService.addCustomer("Leo@gmail.com", "Leo", "Wang");
            customerService.addCustomer("Cleo@gmail.com", "Cleo", "Wang");
            customerService.addCustomer("Maggie@gmail.com", "Maggie", "Wang");
            customerService.addCustomer("leo@gmail.com", "Leo", "Wang");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            customerService.addCustomer("llll", "Leo", "Wu");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            // duplicate email
            customerService.addCustomer("Leo@gmail.com", "Sally", "Wen");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            System.out.println(customerService.getCustomer("Leo@gmail.com"));
            System.out.println(customerService.getCustomer("Wendy@gmail.com"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        System.out.println("Get all customers");
        Collection<Customer> customerList = customerService.getAllCustomers();
        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println(customer);
        }
    }

    public static void testRoom() {
        System.out.println("\n Start testing IRoom");
        List<IRoom> roomList = new ArrayList<>();
        roomList.add(new FreeRoom("101", RoomType.SINGLE));
        roomList.add(new FreeRoom("102", RoomType.DOUBLE));
        roomList.add(new Room("103", 20.5, RoomType.SINGLE));
        roomList.add(new Room("103", 40.5, RoomType.DOUBLE));
        roomList.add(new Room("101", 40.5, RoomType.DOUBLE));
        for (IRoom room : roomList) {
            System.out.println(room);
        }

        System.out.println("Start testing euqals method");
        System.out.println(roomList.get(0).equals(roomList.get(0)));
        System.out.println(roomList.get(0).equals(roomList.get(4)));
        System.out.println(roomList.get(0).equals(roomList.get(2)));
        System.out.println(roomList.get(0).equals(new Customer("123@gmail.com", "Shelly", "Wang")));
    }

    public static void testModelReservation() {
        Customer customer = new Customer("123@gmail.com", "Leo", "Wang");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 10, 14);
        Date checkInDate = calendar.getTime();
        calendar.set(2021, 10, 15);
        Date checkOutDate = calendar.getTime();
        IRoom room = new FreeRoom("101", RoomType.SINGLE);
        Reservation reservation = new Reservation(customer, checkInDate, checkOutDate, room);
        System.out.println(reservation);

        Reservation reservation1 = new Reservation(customer, checkInDate, checkOutDate, room);
        System.out.println(reservation1.equals(reservation));
        calendar.set(2021, 10, 13);
        Date checkInDate2 = calendar.getTime();
        calendar.set(2021, 10, 14);
        Date checkOutDate2 = calendar.getTime();
        Reservation reservation2 = new Reservation(customer, checkInDate2, checkOutDate2, room);
        System.out.println(reservation.equals(reservation2));
    }

    public static void testReservationService() {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.addRoom(new FreeRoom("101", RoomType.SINGLE));
        reservationService.addRoom(new FreeRoom("102", RoomType.DOUBLE));
        //reservationService.displayAllRooms();
        //reservationService.addRoom(new FreeRoom("101", RoomType.SINGLE));
        System.out.println(reservationService.getARoom("101"));
        System.out.println(reservationService.getARoom("103"));

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 10, 14);
        Date checkInDate = calendar.getTime();
        calendar.set(2021, 10, 18);
        Date checkOutDate = calendar.getTime();
        Customer customer = new Customer("Leo@gmail.com", "Leo", "Wang");
        IRoom room = new FreeRoom("101", RoomType.SINGLE);

        Reservation reservation1 = reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        System.out.println(reservation1);

        calendar.set(2021, 10, 11);
        checkInDate = calendar.getTime();
        calendar.set(2021, 10, 12);
        checkOutDate = calendar.getTime();
        Customer customer2 = new Customer("Angela@gmail.com", "Angela", "Wang");
        Reservation reservation2 = reservationService.reserveARoom(customer2, room, checkInDate, checkOutDate);
        System.out.println(reservation2);


        System.out.println("\n List reservations of a customer");
        for (Reservation res : reservationService.getCustomerReservation(customer)) {
            System.out.println(res);
        }


        calendar.set(2021, 10, 19);
        checkInDate = calendar.getTime();
        calendar.set(2021, 10, 20);
        checkOutDate = calendar.getTime();
        Customer customer3 = new Customer("Angela@gmail.com", "Angela", "Wang");
        Reservation reservation3 = reservationService.reserveARoom(customer3, room, checkInDate, checkOutDate);

        System.out.println("Print all reservations");
        reservationService.printAllReservation();
    }

    public static void testReservation() {
        Date[] dates1 = DriverHelper.getDates(new int[]{2021, 10, 8}, new int[]{2021, 10, 9});
        Date[] dates2 = DriverHelper.getDates(new int[]{2021, 10, 10}, new int[]{2021, 10, 15});
        Date[] dates3 = DriverHelper.getDates(new int[]{2021, 10, 17}, new int[]{2021, 10, 19});
        Date[] dates4 = DriverHelper.getDates(new int[]{2021, 10, 22}, new int[]{2021, 10, 25});
        Date[] dates5 = DriverHelper.getDates(new int[]{2021, 10, 13}, new int[]{2021, 10, 16});
        DriverHelper.initializeReservationService();
        DriverHelper.initializeCustomerService();

        ReservationService reservationService = ReservationService.getInstance();
        CustomerService customerService = CustomerService.getInstance();

        // find rooms
        List<IRoom> rooms = reservationService.findRooms(dates1[0], dates1[1]);
        /*for (IRoom room : reservationService.findRooms(dates1[0], dates1[1])) {
            System.out.println(room);
        }*/

        // get a room
        //System.out.println(reservationService.getARoom("10"));

        Customer customer = customerService.getCustomer("Leo@gmail.com");

        // reserve room
        Reservation reservation = reservationService.reserveARoom(customerService.getCustomer("Leo@gmail.com"), rooms.get(0), dates1[0], dates1[1]);
        Reservation reservation2 = reservationService.reserveARoom(customerService.getCustomer("Leo@gmail.com"), rooms.get(0), dates2[0], dates2[1]);
        Reservation reservation3 = reservationService.reserveARoom(customerService.getCustomer("Leo@gmail.com"), rooms.get(0), dates3[0], dates3[1]);
        Reservation reservation4 = reservationService.reserveARoom(customerService.getCustomer("Wang@gmail.com"), rooms.get(1), dates1[0], dates1[1]);
        //Reservation reservation5 = reservationService.reserveARoom(customerService.getCustomer("Leo@gmail.com"), rooms.get(1), dates1[0], dates1[1]);
        reservationService.printAllReservation();
    }

    public static void testAdminApi() {
        AdminResource adminResource = AdminResource.getInstance();
        HotelResource hotelResource = HotelResource.getInstance();
        List<IRoom> rooms = DriverHelper.getRoomList(false, false);
        adminResource.addRoom(rooms);
        List<IRoom> retrievedRooms = adminResource.getAllRooms();

        DriverHelper.initializeCustomerService(); // add some default customers, in which "Leo@gmail.com" has already existed
        hotelResource.createACustomer("Leo@gmail.com", "XX", "Wang");
        List<Customer> retrievedCustomers = (List<Customer>) adminResource.getAllCustomers();
        System.out.println();
    }


}
