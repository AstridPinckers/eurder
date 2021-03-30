package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.Address;

public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String addressLine;
    private String postalCode;
    private String city;

    public CreateUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CreateUserDto setAddress(Address address) {
        this.city = address.getCity();
        this.addressLine = address.getAddressLine();
        this.postalCode = address.getPostalCode();
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
}
