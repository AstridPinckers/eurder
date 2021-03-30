package be.me.eurder.domain.pojos.User;

public class Admin extends User{
    private static final String ROLE = "admin";

    public Admin(String firstName, String lastName, String email, Address address, String phoneNumber,
                 String rawPassword) {
        super(firstName, lastName, email, address, phoneNumber, rawPassword);
    }

    @Override
    public String getRole() {
        return ROLE;
    }
}
