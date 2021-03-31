package be.me.eurder.domain.pojos.user;

public class Customer extends User {

    private static final String ROLE = "customer";

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber,
                    String rawPassword) {
        super(firstName, lastName, email, address, phoneNumber, rawPassword);
    }


    @Override
    public String getRole() {
        return ROLE;
    }
}
