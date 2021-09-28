package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
    HashMap<String, Customer> customers;
    List<Customer> customersList = new ArrayList<>();

    public CustomerService() {
        customers = new HashMap<>();
    }


    public void addCustomer(String email, String firstName, String lastName) {
        email = email.toLowerCase();
        if (!customers.containsKey(email)) {
            Customer newCustomer = new Customer(email, firstName, lastName);
            customers.put(email, newCustomer);
            customersList.add(newCustomer);
        } else {
            throw new IllegalArgumentException("The email: " + email + " already exists");
        }
    }

    /*
        Using a HashMap to implement constant time searching
     */
    public Customer getCustomer(String customerEmail) {
        if (customers.containsKey(customerEmail.toLowerCase())) {
            return customers.get(customerEmail);
        } else {
            throw new IllegalArgumentException("No email matches: " + customerEmail);
        }
    }

    public Collection<Customer> getAllCustomers() {
        return customersList;
    }

}
