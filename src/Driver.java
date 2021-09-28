import model.Customer;
import service.CustomerService;

import java.util.Collection;

public class Driver {

    public static void testModelCustomer() {
        System.out.println("\n Start testing model/Customer \n");
        Customer customer = new Customer("123@gmail.com", "Leo", "Wang");
        System.out.print(customer);
        try {
            Customer invalidCustomer = new Customer("Leo", "Wi", "kfs");
            System.out.println(invalidCustomer);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public static void testCustomerService() {
        System.out.println("\n Start testing service/CustomerService \n");
        CustomerService customerService = new CustomerService();
        try {
            customerService.addCustomer("Leo@gmail.com", "Leo", "Wang");
            customerService.addCustomer("Cleo@gmail.com", "Cleo", "Wang");
            customerService.addCustomer("Maggie@gmail.com", "Maggie", "Wang");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            customerService.addCustomer("llll", "Leo", "Wu");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            // duplicate email
            customerService.addCustomer("Leo@gmail.com", "Sally", "Wen");
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        try {
            System.out.println(customerService.getCustomer("Leo@gmail.com"));
            System.out.println(customerService.getCustomer("Wendy@gmail.com"));
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        System.out.println("Get all customers");
        Collection<Customer> customerList = customerService.getAllCustomers();
        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println(customer);
        }
    }


    public static void main(String[] args) {
        Driver.testModelCustomer();
        Driver.testCustomerService();
    }
}
