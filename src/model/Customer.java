package model;

import java.util.regex.Pattern;

public class Customer {
    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(String email, String firstName, String lastName) {
        if (!pattern.matcher(email).matches()) {
            String warning = "Invalid Email, info provided: " + firstName + " " + lastName + ", email: " + email;
            throw new IllegalArgumentException(warning);
        } else {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        String out = "\n==================\n";
        out += ("Name: " + firstName + " " + lastName + "\n");
        out += ("Email: " + email) + "\n";
        out += "==================\n";
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o.getClass() == this.getClass())) {
            return false;
        }
        Customer customer = (Customer) o;
        return customer.getName().equals(this.getName()) &&
                customer.getEmail().toLowerCase().equals(this.getEmail().toLowerCase());
    }


}
