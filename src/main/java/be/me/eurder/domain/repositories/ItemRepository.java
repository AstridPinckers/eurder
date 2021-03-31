package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.domain.pojos.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemRepository {

    public Item addItem(Item item){
        ItemData.addItem(item);
        return item;
    }
}
