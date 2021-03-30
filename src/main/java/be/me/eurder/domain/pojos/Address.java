package be.me.eurder.domain.pojos;

public class Address {
    private final String addressLine;
    private final String postalCode;
    private final String city;

    public Address(String addressLine, String postalCode, String city) {
        this.addressLine = addressLine;
        this.postalCode = postalCode;
        this.city = city;
    }
}
