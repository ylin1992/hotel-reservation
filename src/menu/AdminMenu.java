package menu;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu extends Menu implements IMenu {

    private static AdminMenu instance = null;
    Scanner scanner;

    private AdminMenu() {
        itemNum = 5;
        scanner = new Scanner(System.in);
    }

    public static AdminMenu getInstance() {
        if (instance == null) {
            instance = new AdminMenu();
        }
        return instance;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdmin Menu");
        System.out.println("-------------------------------------------------\n");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main Menu");
        System.out.println("\n-------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }

    private void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customer in the list.");
        }
    }

    private void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms == null || rooms.isEmpty()) {
            System.out.println("No room has been created");
            return;
        }
        for (IRoom room : rooms) {
            System.out.println(room);
        }
    }

    private void seeAllReservations() {
        adminResource.displayAllReservation();
    }

    private void addARoom() {
        List<IRoom> rooms = new ArrayList<>();
        while (true) {
            String roomNumber = MenuHelper.askRoomNum();
            if (roomNumber == null) break;
            double price = MenuHelper.askPrice();
            RoomType roomType = MenuHelper.askRoomType();
            if (roomType == null) break;

            IRoom room;
            if (price == 0.0) {
                room = new FreeRoom(roomNumber, roomType);
            } else {
                room = new Room(roomNumber, price, roomType);
            }
            rooms.add(room);

            boolean isAgain = MenuHelper.askYesOrNo("Again?");
            if (!isAgain) {
                break;
            }
        }
        adminResource.addRoom(rooms);
    }

    @Override
    public void executeAction(int tag) {
        switch (tag) {
            case 1:
                seeAllCustomers();
                break;
            case 2:
                seeAllRooms();
                break;
            case 3:
                seeAllReservations();
                break;
            case 4:
                addARoom();
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    @Override
    public void startMenu() {
        while (true) {
            displayMenu();
            String num = scanner.next();
            if (num.equals("5")) {
                return;
            }
            try {
                int intNum = Integer.parseInt(num);
                executeAction(intNum);
            } catch (Exception ex) {
                System.out.println("Please input a number between 1 ~ " + itemNum);
            }
        }
    }


}
