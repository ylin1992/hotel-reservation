import driver.DriverHelper;
import menu.MainMenu;

public class App {
    public static void main(String[] args) {
        // Create a data set for testing
        // Uncomment if you want to test the application with some pre-loaded users and rooms
        DriverHelper.initializeCustomerService();
        DriverHelper.initializeReservationService();

        System.out.println("Welcome to the Hotel Reservation Application");
        MainMenu mainMenu = MainMenu.getInstance();
        mainMenu.startMenu();
    }
}
