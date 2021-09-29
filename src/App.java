import driver.DriverHelper;
import menu.MainMenu;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a data set for testing
        DriverHelper.initializeCustomerService();
        DriverHelper.initializeReservationService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Reservation Application");
        MainMenu.displayMenu();

        MainMenu mainMenu = new MainMenu();

        while (true) {
            String num = scanner.next();
            System.out.println(num);
            try {
                int intNum = Integer.parseInt(num);
                mainMenu.executeAction(intNum);
            } catch (Exception ex) {
                System.out.println("Please input a number between 1 ~ 5");
            }
        }
    }
}
