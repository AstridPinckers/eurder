package be.me.eurder.domain.pojos;

public class Price {
    private final String currency;
    private final double value;

    private Price(String currency, double value) {
        this.currency = currency;
        this.value = value;
    }
    public static Price priceInEuros(double value){
        return new Price("EUR",value);
    }

    public String getCurrency() {
        return currency;
    }

    public double getValue() {
        return value;
    }
}
