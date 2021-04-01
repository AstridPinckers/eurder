package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.ItemValidation;
import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.Price;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemData {

    private static Predicate<Item> STOCK_LOW = (item) -> item.getAmount() < 5;
    private static Predicate<Item> STOCK_MEDIUM = (item) -> item.getAmount() < 10 && item.getAmount() >= 5;
    private static Predicate<Item> STOCK_HIGH = (item) -> item.getAmount() >= 10;

    private static List<Item> itemList = new ArrayList<>(List.of(
            new Item("Chocolate", "Milk chocolate with sea salt and caramel", Price.priceInEuros(1.6), 10),
            new Item("Sunglasses", "Aviators", Price.priceInEuros(44.99), 5),
            new Item("Mangoes", "A whole crate full of 'em", Price.priceInEuros(3), 10),
            new Item("Robot vacuum cleaner", "It's doing its best", Price.priceInEuros(129.95), 4),
            new Item("The wellerman", "Sooon maaaaay the wellerman come...", Price.priceInEuros(0.99), 10_000)
    ));

    public static List<Item> getItemList() {
        return itemList.stream().sorted(Comparator.comparingInt(Item::getAmount)).collect(Collectors.toList());
    }
    public static List<Item> getItemsInLowSupply(){
        return itemList.stream().filter(STOCK_LOW).collect(Collectors.toList());
    }
    public static List<Item> getItemsInMediumSupply(){
        return itemList.stream().filter(STOCK_MEDIUM).collect(Collectors.toList());
    }
    public static List<Item> getItemsInHighSupply(){
        return itemList.stream().filter(STOCK_HIGH).collect(Collectors.toList());
    }

    public static Item addItem(Item item) {
        ItemValidation.assertValidItem(item);
        itemList.add(item);
        return item;
    }

    public static Item getItemById(UUID id) {
        for (Item item : itemList) {
            if (item.getUuid().equals(id)) {
                return item;
            }
        }
        return null;
    }

}
