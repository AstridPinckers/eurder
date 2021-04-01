package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.domain.pojos.Item;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemRepository {

    public Item addItem(Item item){
        ItemData.addItem(item);
        return item;
    }

    public List<Item> getAllItems(){
        return ItemData.getItemList();
    }

    public List<Item> getItemsOfUrgency(String urgency){
        switch (urgency.toUpperCase()){
            case "LOW": return ItemData.getItemsInLowSupply();
            case "MEDIUM": return ItemData.getItemsInMediumSupply();
            case "HIGH": return ItemData.getItemsInHighSupply();
            default: return null;
        }
    }
}
