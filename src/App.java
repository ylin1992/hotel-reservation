import menu.AdminMenu;
import menu.MainMenu;

public class App {
    public static void main(String[] args) {
        // Create a data set for testing
        //DriverHelper.initializeCustomerService();
        //DriverHelper.initializeReservationService();

        System.out.println("Welcome to the Hotel Reservation Application");
        MainMenu mainMenu = MainMenu.getInstance();
        AdminMenu adminMenu = AdminMenu.getInstance();
        mainMenu.startMenu();
    }
}
