package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.Price;

import java.time.LocalDate;
import java.util.UUID;

public class ReceiveItemGroupDto {

    private String itemName;
    private int amount;
    private Price pricePerUnit;
    private LocalDate shippingDate;
    private Price price;

    public ReceiveItemGroupDto setPrice() {
        this.price = calculatePrice();
        return this;
    }

    public ReceiveItemGroupDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public ReceiveItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ReceiveItemGroupDto setPricePerUnit(Price pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        return this;
    }

    public ReceiveItemGroupDto setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public String getItemName() {
        return itemName;
    }

    public int getAmount() {
        return amount;
    }

    public Price getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    private Price calculatePrice(){
       return Price.priceInEuros(getAmount()*getPricePerUnit().getValue());
    }

    public Price getPrice() {
        return price;
    }
}
