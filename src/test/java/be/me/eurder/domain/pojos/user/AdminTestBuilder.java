package be.me.eurder.domain.pojos.user;

public class AdminTestBuilder {

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "email";
    private Address address = new Address("addressLine", "postalCode", "city");
    private String phoneNumber = "phoneNumber";
    private String rawPassword = "rawPassword";

    private AdminTestBuilder() {

    }

    public static AdminTestBuilder admin() {
        return new AdminTestBuilder();
    }

    public Admin build() {
        return new Admin(firstName, lastName, email, address, phoneNumber, rawPassword);
    }

    public AdminTestBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public AdminTestBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AdminTestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public AdminTestBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public AdminTestBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public AdminTestBuilder rawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }
}