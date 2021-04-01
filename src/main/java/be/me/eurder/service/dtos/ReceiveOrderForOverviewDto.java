package be.me.eurder.service.dtos;

import java.util.Set;

public class ReceiveOrderForOverviewDto {
    private String id;
    private Set<ReceiveItemGroupDto> itemGroupSet;
    private double totalPrice;

    public ReceiveOrderForOverviewDto setId(String id) {
        this.id = id;
        return this;
    }

    public ReceiveOrderForOverviewDto setItemGroupSet(Set<ReceiveItemGroupDto> itemGroupSet) {
        this.itemGroupSet = itemGroupSet;
        return this;
    }

    public ReceiveOrderForOverviewDto setTotalPrice() {
        this.totalPrice = calculateTotalPrice();
        return this;
    }

    public Set<ReceiveItemGroupDto> getItemGroupSet() {
        return itemGroupSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getId() {
        return id;
    }

    private double calculateTotalPrice(){
        double result = 0;
        for(ReceiveItemGroupDto itemGroupDto : itemGroupSet ){
            result += itemGroupDto.getPrice().getValue();
        }
        return result;
    }
}
