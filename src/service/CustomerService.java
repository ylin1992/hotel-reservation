package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
    HashMap<String, Customer> customers;
    List<Customer> customersList = new ArrayList<>();

    private static CustomerService instance = null;

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    private CustomerService() {
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

    public Customer getCustomer(String customerEmail) {
        
        if (customerEmail != null && customers.containsKey(customerEmail.toLowerCase())) {
            return customers.get(customerEmail.toLowerCase());
        } else {
            throw new IllegalArgumentException("Email hasn't been used: " + customerEmail);
        }
    }

    public Collection<Customer> getAllCustomers() {
        return customersList;
    }

}
