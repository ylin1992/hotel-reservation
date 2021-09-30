package menu;

import model.Customer;
import model.IRoom;
import model.Reservation;
import utils.DateHelper;

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
        System.out.println("\n-------------------------------------------------\n");
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
        while (true) {
            checkInDate = MenuHelper.askDate("When do you want to check in? (yyyy/mm/dd)");
            if (checkInDate == null) return;
            checkOutDate = MenuHelper.askDate("When do you want to check out? (yyyy/mm/dd)");
            if (checkOutDate == null) return;
            rooms = hotelResource.findARoom(checkInDate, checkOutDate);
            if (rooms == null) {
                System.out.println("No room is available, looking for next week...");
                rooms = hotelResource.findARoom(DateHelper.addDate(checkInDate, 7), DateHelper.addDate(checkOutDate, 7));
                if (rooms == null) {
                    boolean isAgain = MenuHelper.askYesOrNo("Do you want to search again?");
                    if (!isAgain) {
                        return;
                    }
                } else {
                    System.out.println("We got some rooms available next week: ");
                    hotelResource.displayRooms(rooms);
                    boolean isAgain = MenuHelper.askYesOrNo("Do you want to search for next week ? (y/n)");
                    if (!isAgain) {
                        return;
                    }
                }
            } else {
                break;
            }
        }

        System.out.println("Available rooms: ");
        hotelResource.displayRooms(rooms);

        Reservation reservation = MenuHelper.askReservation(checkInDate, checkOutDate, customer.getEmail());
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

    public static void createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        String userEmail;
        System.out.println("Your email: (enter exit to quit)");
        userEmail = scanner.next();
        if (userEmail.equals("exit")) return;

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
