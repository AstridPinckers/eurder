package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.user.Address;

public class ReceiveUserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String addressLine;
    private String postalCode;
    private String city;
    private String role;

    public ReceiveUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ReceiveUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ReceiveUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public ReceiveUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ReceiveUserDto setAddress(Address address) {
        this.city = address.getCity();
        this.addressLine = address.getAddressLine();
        this.postalCode = address.getPostalCode();
        return this;
    }
    public ReceiveUserDto setRole(String role) {
        this.role=role;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getRole() {
        return role;
    }
}
