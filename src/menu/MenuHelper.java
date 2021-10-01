package menu;

import model.Customer;
import model.IRoom;
import model.RoomType;
import utils.DateHelper;
import utils.FormatHelper;

import java.util.Collection;
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
            if (!FormatHelper.emailMatcher(userEmail)) {
                System.out.println("Wrong email format, please enter again");
                continue;
            }
            if (userEmail.equals("exit")) return null;
            return getCustomerByEmail(userEmail);
        }
    }

    public static IRoom askRoom() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a room number: (enter exit to quit)");
            String inputRoomNumber = scanner.next();
            if (inputRoomNumber.equals("exit")) {
                return null;
            }
            IRoom room = hotelResource.getRoom(inputRoomNumber);
            if (room == null) {
                System.out.println("No matched room was found, try again");
            } else {
                return room;
            }
        }
    }

    public static boolean isSameDate(IRoom room, Date checkInDate, Date checkOutDate) {
        Date[] dates = room.getAvailableDates();
        return (dates[0].compareTo(checkInDate) == 0 && dates[1].compareTo(checkOutDate) == 0);
    }

    //public static Reservation askReservation(Date checkInDate, Date checkOutDate, String email, Collection<IRoom> rooms) {
    //    Scanner scanner = new Scanner(System.in);
    //    while (true) {
    //        System.out.println("Enter a room number: (enter exit to quit)");
    //
    //        String inputRoomNumber = scanner.next();
    //        if (inputRoomNumber.equals("exit")) {
    //            return null;
    //        }
    //
    //        try {
    //            IRoom room = hotelResource.getRoom(inputRoomNumber);
    //            Date[] recommendedDates = room.getAvailableDates();
    //            if (room != null && recommendedDates[0].compareTo(checkInDate) != 0) {
    //                System.out.println("Applying recommended date...");
    //                checkInDate = recommendedDates[0];
    //                checkOutDate = recommendedDates[1];
    //            }
    //            return hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
    //        } catch (Exception ex) {
    //            System.out.println(ex.getLocalizedMessage());
    //            System.out.println("Try again.");
    //        }
    //    }
    //}

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

    public static String askRoomNum() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter room number: (enter exit to quit)");
            String roomNumber = scanner.next();
            if (roomNumber.equals("exit")) {
                return null;
            }
            if (roomNumber.length() > 0) {
                IRoom room = hotelResource.getRoom(roomNumber);
                if (room == null) {
                    return roomNumber;
                } else {
                    System.out.println("The room number has been used, pick another name.");
                }
            } else {
                System.out.println("Please make sure your input is not empty");
            }
        }
    }

    public static Date[] askDates() {
        Collection<IRoom> rooms;
        Date checkInDate;
        Date checkOutDate;

        while (true) {
            checkInDate = MenuHelper.askDate("When do you want to check in? (yyyy/mm/dd)");
            if (checkInDate == null) return null;
            checkOutDate = MenuHelper.askDate("When do you want to check out? (yyyy/mm/dd)");
            if (checkOutDate == null) return null;
            if (checkInDate.compareTo(checkOutDate) >= 0) {
                System.out.println("Check in date is later than or identical to check in date, try again");
            } else {
                return new Date[]{checkInDate, checkOutDate};
            }
        }
    }

    public static double askPrice() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter price: (in number)");
            String priceInString = scanner.next();
            try {
                return Double.parseDouble(priceInString);
            } catch (Exception ex) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public static RoomType askRoomType() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Single or Double bed? (Single: s, Double: d, Exit: exit)");
            String type = scanner.next();
            if (type.equals("d")) {
                return RoomType.DOUBLE;
            } else if (type.equals("s")) {
                return RoomType.SINGLE;
            } else if (type.equals("exit")) {
                return null;
            } else {
                System.out.println("Please make sure your input is valid (Single: s, Double: d, Exit: exit)");
            }
        }
    }

}
