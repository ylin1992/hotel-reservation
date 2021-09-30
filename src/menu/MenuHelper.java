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
            System.out.println(title + " (enter exit to quit)");
            String inputDate = scanner.next();
            if (inputDate.equals("exit")) return null;
            try {
                DateHelper.checkInputFormat(inputDate);
                return DateHelper.stringToDate(inputDate);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }

    public static Customer getCustomerByEmail(String userEmail) {
        try {
            Customer customer = adminResource.getCustomer(userEmail);
            return customer;
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
            return null;
        }
    }

    public static Customer askEmail() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Your email: (enter exit to quit)");
            String userEmail = scanner.next();
            if (userEmail.equals("exit")) return null;
            return getCustomerByEmail(userEmail);
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

    public static boolean askYesOrNo(String title) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(title);
            String yesOrNo = scanner.next();
            int validateResult = validateYesOrNo(yesOrNo);
            if (validateResult == -1) {
                System.out.println("Please enter y/yes/n/no");
                continue;
            } else if (validateResult == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * @param yesOrNo
     * @return 1: if answer is yes
     * 0: if answer is no
     * -1: if answer is invalid
     */
    private static int validateYesOrNo(String yesOrNo) {
        yesOrNo = yesOrNo.toLowerCase();
        if (yesOrNo.equals("y") || yesOrNo.equals("yes")) {
            return 1;
        } else if (yesOrNo.equals("n") || yesOrNo.equals("no")) {
            return 0;
        } else {
            return -1;
        }
    }


    public static Customer createAccount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Your email: (enter exit to quit)");
            String userEmail = scanner.next();
            if (userEmail.equals("exit")) return null;

            Customer customer = getCustomerByEmail(userEmail);
            if (customer != null) {
                System.out.println("You are already in the list");
                return customer;
            }

            System.out.println("Please enter your first name: ");
            String firstName = scanner.next();

            System.out.println("Please enter your last name: ");
            String lastName = scanner.next();

            try {
                hotelResource.createACustomer(userEmail, firstName, lastName);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}
