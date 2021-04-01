package be.me.eurder.service.mappers;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.Order;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import be.me.eurder.service.dtos.CreateOrderDto;
import be.me.eurder.service.dtos.ReceiveOrderDto;

import java.util.Optional;

public class OrderMapper {

    public static Order createOrderDto_to_Order(CreateOrderDto createOrderDto, Optional<String> email){
        User user = UserData.getUserByEmail(email.orElse(null));
        Customer customer = Customer.returnCustomerOrThrowException(user);
        return new Order(customer,ItemGroupMapper.map_CreateItemGroupDto_to_ItemGroup(createOrderDto.getItemGroupSet()));
    }

    public static ReceiveOrderDto order_to_ReceiveOrderDto(Order order){
        return new ReceiveOrderDto()
                .setCustomer(order.getCustomer())
               .setItemGroupSet(ItemGroupMapper.map_ItemGroup_to_ReceiveItemGroupDto(order.getItemGroupSet()))
                .setTotalPrice();
    }

}
