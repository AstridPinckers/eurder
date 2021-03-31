package be.me.eurder.domain.pojos;

import java.util.UUID;

public class Item {

    private final UUID uuid;
    private final String name;
    private final String description;
    private Price price;
    private int amount;

    public Item(String name, String description, Price price, int amount) {
        this.uuid=UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public UUID getUuid() {
        return uuid;
    }
}
