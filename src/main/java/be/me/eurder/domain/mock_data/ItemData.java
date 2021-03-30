package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.ItemValidation;
import be.me.eurder.domain.pojos.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class ItemData {

    private static List<Item> itemList = new ArrayList<>(List.of());

    public static List<Item> getItemList() {
        return itemList;
    }

    public static Item addItem(Item item){
        ItemValidation.assertValidItem(item);
        itemList.add(item);
        return item;
    }

}
