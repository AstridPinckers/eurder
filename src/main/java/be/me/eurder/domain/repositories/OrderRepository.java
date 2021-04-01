package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.OrderData;
import be.me.eurder.domain.pojos.Order;
import be.me.eurder.domain.pojos.user.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository {

    public Order addOrder(Order order) {
        return OrderData.addOrder(order);
    }

    public List<Order> getOrdersByCustomer(Customer customer){
        return OrderData.getOrdersByCustomer(customer);
    }
}
