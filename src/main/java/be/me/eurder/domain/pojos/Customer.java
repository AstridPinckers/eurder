package be.me.eurder.domain.pojos;

public class Customer extends User {

    private static final String ROLE = "customer";

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        super(firstName, lastName, email, address, phoneNumber);
    }

    @Override
    public String getRole() {
        return ROLE;
    }
}
