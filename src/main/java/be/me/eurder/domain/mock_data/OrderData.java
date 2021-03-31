package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.OrderValidation;
import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderData {

    private static List<Order> orderList = new ArrayList<>(List.of());

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static Order addOrder(Order order){
        OrderValidation.assertValidOrder(order);
        orderList.add(order);
        return order;
    }


}
