package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.ItemGroup;
import be.me.eurder.domain.pojos.Price;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public class CreateOrderDto {

    private Set<CreateItemGroupDto> itemGroupSet;



    public CreateOrderDto setItemGroupSet(Set<CreateItemGroupDto> itemGroupSet) {
        this.itemGroupSet = itemGroupSet;
        return this;
    }

    public Set<CreateItemGroupDto> getItemGroupSet() {
        return itemGroupSet;
    }

}
