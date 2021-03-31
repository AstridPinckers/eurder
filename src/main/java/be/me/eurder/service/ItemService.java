package be.me.eurder.service;

import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.repositories.ItemRepository;
import be.me.eurder.service.dtos.CreateItemDto;
import be.me.eurder.service.dtos.ReceiveItemDto;
import be.me.eurder.service.mappers.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    Logger logger = LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ReceiveItemDto createItem(CreateItemDto createItemDto){
        logger.info("New Item added");
        Item item = itemRepository.addItem(ItemMapper.createItemDto_to_item(createItemDto));
        return ItemMapper.item_to_ReceiveItemDto(item);
    }
}
