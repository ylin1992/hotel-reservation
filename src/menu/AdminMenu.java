package menu;

import model.Customer;

import java.util.Collection;
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

    public static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } else {
            System.out.println("No customer in the list.");
        }
    }


    @Override
    public void executeAction(int tag) {

        switch (tag) {
            case 1:
                seeAllCustomers();
                break;
            case 2:
                System.out.println("Case 2");
                break;
            case 3:
                System.out.println("Case 3");
                break;
            case 4:
                System.out.println("Case 4");
                break;
            default:
                System.out.println("default");
                break;
        }
    }

    @Override
    public void startMenu() {
        displayMenu();
        String num = scanner.next();
        try {
            int intNum = Integer.parseInt(num);
            executeAction(intNum);
        } catch (Exception ex) {
            System.out.println("Please input a number between 1 ~ " + itemNum);
        }
    }


}
