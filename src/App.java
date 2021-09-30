import driver.DriverHelper;
import menu.AdminMenu;
import menu.MainMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a data set for testing
        DriverHelper.initializeCustomerService();
        DriverHelper.initializeReservationService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Reservation Application");
        MainMenu mainMenu = MainMenu.getInstance();
        AdminMenu adminMenu = AdminMenu.getInstance();
        while (true) {
            mainMenu.displayMenu();
            String num = scanner.next();
            try {
                int intNum = Integer.parseInt(num);
                mainMenu.checkTagValidity(intNum);
                if (intNum != 4) {
                    mainMenu.executeAction(intNum);
                } else {
                    adminMenu.displayMenu();
                }
            } catch (Exception ex) {
                System.out.println("Please input a number between 1 ~ 5");
            }
        }
    }
}
