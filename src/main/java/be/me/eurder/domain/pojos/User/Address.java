package be.me.eurder.domain.pojos.User;

public class Address {
    private final String addressLine;
    private final String postalCode;
    private final String city;

    public Address(String addressLine, String postalCode, String city) {
        this.addressLine = addressLine;
        this.postalCode = postalCode;
        this.city = city;
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
