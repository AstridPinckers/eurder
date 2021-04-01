package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.Price;

import java.util.Set;

public class ReceiveOrdersPerCustomerDto {

    private Set<ReceiveOrderForOverviewDto> orderSet;
    private Price price;

    public ReceiveOrdersPerCustomerDto setOrderSet(Set<ReceiveOrderForOverviewDto> orderSet) {
        this.orderSet = orderSet;
        return this;
    }

    public ReceiveOrdersPerCustomerDto setPrice() {
        this.price = price;
        return this;
    }
    private Price calculateTotalPrice(){
        double result = 0;
        for(ReceiveOrderForOverviewDto order: orderSet){
            result += order.getTotalPrice();
        }
        return Price.priceInEuros(result);
    }
}
