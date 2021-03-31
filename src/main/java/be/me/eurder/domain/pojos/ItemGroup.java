package be.me.eurder.domain.pojos;

import be.me.eurder.domain.mock_data.ItemData;

import java.time.LocalDate;
import java.util.UUID;

public class ItemGroup {
    private UUID id;
    private UUID itemId;
    private int amount;
    private Price price;
    private LocalDate shippingDate;


    public ItemGroup(UUID itemId, int amount) {
        id = UUID.randomUUID();
        this.itemId = itemId;
        this.amount = amount;
        price = ItemData.getItemById(itemId).getPrice();
        shippingDate = isInStock() ? LocalDate.now().plusDays(1) :
                LocalDate.now().plusDays(7);
    }

    private boolean isInStock() {
        return ItemData.getItemById(itemId).getAmount() - amount >= 0;
    }

    public UUID getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public Price getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }


}
