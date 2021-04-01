package be.me.eurder.service.dtos;

public class ReceiveItemDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private int amount;
    private String currency;

    public ReceiveItemDto setId(String id) {
        this.id = id;
        return this;
    }

    public ReceiveItemDto setName(String name){
        this.name = name;
        return this;
    }

    public ReceiveItemDto setDescription(String description){
        this.description = description;
        return this;
    }

    public ReceiveItemDto setPrice(double price){
        this.price = price;
        return this;
    }
    public ReceiveItemDto setAmount(int amount){
        this.amount = amount;
        return this;
    }

    public ReceiveItemDto setCurrency(String currency){
        this.currency = currency;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getId() {
        return id;
    }
}
