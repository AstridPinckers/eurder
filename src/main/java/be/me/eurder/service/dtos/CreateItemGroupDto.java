package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.Price;

import java.time.LocalDate;
import java.util.UUID;

public class CreateItemGroupDto {
    private String itemId;
    private int amount;

    public CreateItemGroupDto setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public CreateItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }


}
