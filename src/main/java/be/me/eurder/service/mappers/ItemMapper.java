package be.me.eurder.service.mappers;

import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.Price;
import be.me.eurder.service.dtos.CreateItemDto;
import be.me.eurder.service.dtos.ReceiveItemDto;

public class ItemMapper {

    public static Item createItemDto_to_item(CreateItemDto createItemDto){
        Price price = Price.priceInEuros(createItemDto.getPrice());
        return new Item(createItemDto.getName(), createItemDto.getDescription(), price, createItemDto.getAmount());
    }

    public static ReceiveItemDto item_to_ReceiveItemDto(Item item){
        return new ReceiveItemDto()
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setAmount(item.getAmount())
                .setCurrency(item.getPrice().getCurrency())
                .setPrice(item.getPrice().getValue())
                .setId(item.getUuid().toString());
    }
}
