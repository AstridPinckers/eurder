package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.OrderData;
import be.me.eurder.domain.pojos.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRepository {

    public Order addOrder(Order order) {
        return OrderData.addOrder(order);
    }
}
