package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.ItemValidation;
import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.Price;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemData {

    private static List<Item> itemList = new ArrayList<>(List.of(
            new Item("Chocolate", "Milk chocolate with sea salt and caramel", Price.priceInEuros(1.6),10)
    ));

    public static List<Item> getItemList() {
        return itemList;
    }

    public static Item addItem(Item item){
        ItemValidation.assertValidItem(item);
        itemList.add(item);
        return item;
    }

    public static Item getItemById(UUID id){
        for (Item item : itemList){
            if (item.getUuid().equals(id)){
                return item;
            }
        }
        return null;
    }

}
