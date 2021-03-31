package be.me.eurder.service.dtos;

public class CreateItemDto {

    private String name;
    private String description;
    private double price;
    private int amount;

    public CreateItemDto setName(String name){
        this.name = name;
        return this;
    }

    public CreateItemDto setDescription(String description){
        this.description = description;
        return this;
    }

    public CreateItemDto setPrice(double price){
        this.price = price;
        return this;
    }
    public CreateItemDto setAmount(int amount){
        this.amount = amount;
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
}
