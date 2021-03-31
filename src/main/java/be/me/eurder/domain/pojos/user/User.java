package be.me.eurder.domain.pojos.user;

import be.me.eurder.infrastructure.PassWordEncryption;

import java.util.UUID;

public abstract class User {
    private final UUID uuid;
    private final String firstName;
    private final String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String encryptedPassword;
    private byte[] salt;

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

    public byte[] getSalt() {
        return salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setEncryptedPassword(String rawPassword) {
        this.salt = PassWordEncryption.getSalt();
        this.encryptedPassword = PassWordEncryption.generateEncryptedPassword(rawPassword,salt);
    }
}
