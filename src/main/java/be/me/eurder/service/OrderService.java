package be.me.eurder.service;

import be.me.eurder.domain.pojos.Order;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import be.me.eurder.domain.repositories.OrderRepository;
import be.me.eurder.domain.repositories.UserRepository;
import be.me.eurder.service.dtos.CreateOrderDto;
import be.me.eurder.service.dtos.ReceiveOrderDto;
import be.me.eurder.service.dtos.ReceiveOrdersPerCustomerDto;
import be.me.eurder.service.mappers.OrderMapper;
import be.me.eurder.service.mappers.OrdersByCustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public ReceiveOrderDto createOrder(CreateOrderDto createOrderDto, Optional<String> email){
        logger.info("New order placed");
        Order order = orderRepository.addOrder(OrderMapper.createOrderDto_to_Order(createOrderDto, email));
        return OrderMapper.order_to_ReceiveOrderDto(order);
    }

    public ReceiveOrdersPerCustomerDto getOrdersByCustomerEmail(Optional<String> email){
        logger.info("User overview orders is requested");
        User user = userRepository.getUserByEmail(email.orElse(null));
        Customer customer = Customer.returnCustomerOrThrowException(user);
        List<Order> orderList = orderRepository.getOrdersByCustomer(customer);
        return OrdersByCustomerMapper.listOfOrders_to_ReceiveOrdersPerCustomerDto(orderList);
    }
}
