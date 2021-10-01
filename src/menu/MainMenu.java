package menu;

import model.Customer;
import model.IRoom;
import model.Reservation;
import utils.FormatHelper;

import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu extends Menu implements IMenu {

    String dateRegix;
    Scanner scanner;
    private static MainMenu instance = null;

    private MainMenu() {
        super();
        dateRegix = "^\\d{4}/\\d{2}/\\d{2}$";
        itemNum = 5;
        scanner = new Scanner(System.in);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    @Override
    public void displayMenu() {
        System.out.println("Main menu");
        System.out.println("-------------------------------------------------\n");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("\n-------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    private void reserveARoom() {
        // user log in
        Customer customer = MenuHelper.askEmail();
        if (customer == null) return;

        // find a room
        Collection<IRoom> rooms;
        Date checkInDate;
        Date checkOutDate;
        IRoom room;
        Reservation reservation = null;
        // find a room
        while (true) {
            Date[] dates = MenuHelper.askDates();
            if (dates == null) return;
            checkInDate = dates[0];
            checkOutDate = dates[1];
            rooms = hotelResource.findARoom(checkInDate, checkOutDate);
            System.out.println("Your recommended rooms and available dates are listed as follows: ");
            hotelResource.displayRooms(rooms);

            room = MenuHelper.askRoom();
            if (room == null) return;
            if (!MenuHelper.isSameDate(room, checkInDate, checkOutDate)) {
                System.out.println("The room number you provided is not available in your travel dates");
                System.out.println("Do you want us to automatically apply your travel dates as the recommended dates? ");
                System.out.println("Recommended date: " + room.getAvailableDates()[0] + " ~ " + room.getAvailableDates()[1]);
                boolean yesOrNo = MenuHelper.askYesOrNo("press n to search again, press y to apply dates");
                if (yesOrNo) {
                    checkInDate = room.getAvailableDates()[0];
                    checkOutDate = room.getAvailableDates()[1];
                    break;
                }
            } else {
                break;
            }
        }

        try {
            reservation = hotelResource.bookARoom(customer.getEmail(), room, checkInDate, checkOutDate);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }


        //Reservation reservation = MenuHelper.askReservation(checkInDate, checkOutDate, customer.getEmail(), rooms);
        if (reservation == null) return; // user quits the program
        System.out.println("Success, your reservation info is listed as follows");
        System.out.println(reservation);
    }


    private static void seeReservations() {
        Customer customer = MenuHelper.askEmail();
        if (customer == null) return;

        Collection<Reservation> reservations = hotelResource.getCustomerReservations(customer.getEmail());
        if (reservations != null) {
            System.out.println("Your reservations: ");
            hotelResource.displayReservations(reservations);
        }
    }

    private static void createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        String userEmail;
        while (true) {
            System.out.println("Your email: (enter exit to quit)");
            userEmail = scanner.next();
            if (userEmail.equals("exit")) return;
            if (!FormatHelper.emailMatcher(userEmail)) {
                System.out.println("Wrong email format, please type again.");
                continue;
            }
            break;
        }

        Customer customer = MenuHelper.getCustomerByEmail(userEmail);
        if (customer != null) {
            System.out.println("You are already in the list");
            return;
        }

        System.out.println("Please enter your first name: ");
        String firstName = scanner.next();

        System.out.println("Please enter your last name: ");
        String lastName = scanner.next();

        try {
            hotelResource.createACustomer(userEmail, firstName, lastName);
            System.out.println("Success");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }


    }

    @Override
    public void executeAction(int tag) {

        switch (tag) {
            case 1:
                reserveARoom();
                break;
            case 2:
                seeReservations();
                break;
            case 3:
                createAnAccount();
                break;
            default:
                System.out.println("GoodBye!");
                System.exit(0);
                break;
        }
    }

    @Override
    public void startMenu() {
        AdminMenu adminMenu = AdminMenu.getInstance();

        while (true) {
            displayMenu();
            String num = scanner.next();
            try {
                int intNum = Integer.parseInt(num);
                checkTagValidity(intNum);
                if (intNum != 4) {
                    executeAction(intNum);
                } else {
                    adminMenu.startMenu();
                }
            } catch (Exception ex) {
                System.out.println("Please input a number between 1 ~ " + itemNum);
            }
        }
    }

}
