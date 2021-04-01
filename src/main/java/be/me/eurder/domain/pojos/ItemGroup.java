package be.me.eurder.domain.pojos;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.infrastructure.exceptions.NotFoundInDatabaseException;

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
        price = getItemById(itemId).getPrice();
        shippingDate = isInStock() ? LocalDate.now().plusDays(1) :
                LocalDate.now().plusDays(7);
    }

    public static Item getItemById(UUID itemId) {
        Item item =  ItemData.getItemById(itemId);
        if(item==null){
            throw new NotFoundInDatabaseException("item");
        }
        return item;
    }

    private boolean isInStock() {
        return getItemById(itemId).getAmount() - amount >= 0;
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
