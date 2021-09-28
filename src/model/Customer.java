package model;

import java.util.regex.Pattern;

public class Customer {
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);

    String firstName;
    String lastName;
    String email;

    public Customer(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
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

    @Override
    public String toString() {
        String out = "\n==================\n";
        out += ("Name: " + firstName + " " + lastName + "\n");
        out += ("Email: " + email) + "\n";
        out += "==================\n";
        return out;
    }
}
