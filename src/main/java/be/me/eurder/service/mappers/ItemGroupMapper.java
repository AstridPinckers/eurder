package be.me.eurder.service.mappers;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.domain.pojos.ItemGroup;
import be.me.eurder.service.dtos.CreateItemGroupDto;
import be.me.eurder.service.dtos.ReceiveItemGroupDto;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ItemGroupMapper {

    public static ItemGroup createItemGroupDto_to_ItemGroup(CreateItemGroupDto createItemGroupDto){
        return new ItemGroup(UUID.fromString(createItemGroupDto.getItemId()), createItemGroupDto.getAmount());
    }

    public static Set<ItemGroup> map_CreateItemGroupDto_to_ItemGroup(Set<CreateItemGroupDto> createItemGroupDtos){
        return createItemGroupDtos.stream().map(ItemGroupMapper::createItemGroupDto_to_ItemGroup).collect(Collectors.toSet());
    }

    public static ReceiveItemGroupDto itemGroup_to_ReceiveItemGroupDto(ItemGroup itemGroup){
        return new ReceiveItemGroupDto()
                .setItemName(ItemGroup.getItemById(itemGroup.getItemId()).getName())
                .setAmount(itemGroup.getAmount())
                .setPricePerUnit(itemGroup.getPrice())
                .setShippingDate(itemGroup.getShippingDate())
                .setPrice();
    }

    public static Set<ReceiveItemGroupDto> map_ItemGroup_to_ReceiveItemGroupDto(Set<ItemGroup> itemGroupSet){
        return itemGroupSet.stream().map(ItemGroupMapper::itemGroup_to_ReceiveItemGroupDto).collect(Collectors.toSet());
    }


}
