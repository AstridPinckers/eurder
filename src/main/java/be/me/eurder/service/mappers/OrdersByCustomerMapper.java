package be.me.eurder.service.mappers;

import be.me.eurder.domain.pojos.ItemGroup;
import be.me.eurder.domain.pojos.Order;
import be.me.eurder.service.dtos.ReceiveItemGroupDto;
import be.me.eurder.service.dtos.ReceiveOrderForOverviewDto;
import be.me.eurder.service.dtos.ReceiveOrdersPerCustomerDto;

import java.util.List;
import java.util.stream.Collectors;

public class OrdersByCustomerMapper {

    public static ReceiveOrdersPerCustomerDto listOfOrders_to_ReceiveOrdersPerCustomerDto(List<Order> orderList){
        return new ReceiveOrdersPerCustomerDto()
                .setOrderSet(orderList.stream().map(OrdersByCustomerMapper::Order_to_ReceiveOrderForOverviewDto).collect(Collectors.toSet()))
                .setPrice();
    }

    public static ReceiveOrderForOverviewDto Order_to_ReceiveOrderForOverviewDto(Order order){
        return new ReceiveOrderForOverviewDto()
                .setId(order.getUuid().toString())
                .setItemGroupSet(order.getItemGroupSet().stream().map(OrdersByCustomerMapper::itemGroup_to_ReceiveItemGroupDto).collect(Collectors.toSet()))
                .setTotalPrice();
    }
    public static ReceiveItemGroupDto itemGroup_to_ReceiveItemGroupDto(ItemGroup itemGroup){
        return new ReceiveItemGroupDto()
                .setItemName(ItemGroup.getItemById(itemGroup.getItemId()).getName())
                .setAmount(itemGroup.getAmount())
                .setPricePerUnit(itemGroup.getPrice())
                .setPrice();
    }
}
