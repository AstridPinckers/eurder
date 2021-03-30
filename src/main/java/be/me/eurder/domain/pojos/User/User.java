package be.me.eurder.domain.pojos.User;

import be.me.eurder.domain.PassWordEncryption;

import java.util.UUID;

public abstract class User {
    private final UUID uuid;
    private final String firstName;
    private final String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String encryptedPassword;
    private UUID salt;

    public User(String firstName, String lastName, String email, Address address, String phoneNumber,
                String rawPassword) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setEncryptedPassword(rawPassword);
    }

    public abstract String getRole();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEncryptedPassword(String rawPassword) {
        this.salt = UUID.randomUUID();
        this.encryptedPassword = PassWordEncryption.generateEncryptedPassword(rawPassword,salt);
    }
}
