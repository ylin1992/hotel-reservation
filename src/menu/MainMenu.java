package menu;

import exception.UndefinedActionException;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class MainMenu extends Menu implements IMenu {

    String dateRegix;

    public MainMenu() {
        super();
        dateRegix = "^\\d{4}/\\d{2}/\\d{2}$";
    }


    public static void displayMenu() {
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
        Date checkInDate = MenuHelper.askDate("When do you want to check in? (yyyy/mm/dd)");
        Date checkOutDate = MenuHelper.askDate("When do you want to check out? (yyyy/mm/dd)");

        Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);
        System.out.println("Rooms that apply: ");
        hotelResource.displayRooms(rooms);

        Reservation reservation = MenuHelper.askReservation(checkInDate, checkOutDate, customer.getEmail());
        if (reservation == null) return; // user quits the program
        System.out.println("Reservation info");
        System.out.println(reservation);
    }

    @Override
    public void executeAction(int tag) {
        switch (tag) {
            case 1:
                reserveARoom();
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    public void checkTagValidity(int tag) throws UndefinedActionException {
        if (tag < 1 || tag > 5) {
            throw new UndefinedActionException("");
        }
    }
}
