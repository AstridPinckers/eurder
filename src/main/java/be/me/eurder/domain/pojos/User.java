package be.me.eurder.domain.pojos;

public abstract class User {
    private final String firstName;
    private final String lastName;
    private String email;
    private Address address;
    private String phoneNumber;

    public User(String firstName, String lastName, String email, Address address, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getRole();
}
