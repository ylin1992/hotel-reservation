import model.*;
import service.CustomerService;

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
        CustomerService customerService = new CustomerService();
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

    public static void main(String[] args) {
        //Driver.testModelCustomer();
        //Driver.testCustomerService();
        //testRoom();
        testModelReservation();
    }
}
