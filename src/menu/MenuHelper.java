package menu;

import model.Customer;
import model.IRoom;
import model.Reservation;
import utils.DateHelper;

import java.util.Date;
import java.util.Scanner;

public class MenuHelper extends Menu {

    public static Date askDate(String title) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(title);
            String inputDate = scanner.next();
            try {
                DateHelper.checkInputFormat(inputDate);
                return DateHelper.stringToDate(inputDate);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    public static Customer askEmail() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Your email: ");
            String userEmail = scanner.next();
            try {
                Customer customer = adminResource.getCustomer(userEmail);
                return customer;
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
                return null;
            }
        }
    }

    public static Reservation askReservation(Date checkInDate, Date checkOutDate, String email) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a room number: (enter exit to quit)");
            String inputRoomNumber = scanner.next();
            if (inputRoomNumber.equals("exit")) {
                return null;
            }
            try {
                IRoom room = hotelResource.getRoom(inputRoomNumber);
                return hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

}
