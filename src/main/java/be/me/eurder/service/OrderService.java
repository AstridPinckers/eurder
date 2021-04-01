package be.me.eurder.service;

import be.me.eurder.domain.pojos.Order;
import be.me.eurder.domain.repositories.OrderRepository;
import be.me.eurder.service.dtos.CreateOrderDto;
import be.me.eurder.service.dtos.ReceiveOrderDto;
import be.me.eurder.service.mappers.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ReceiveOrderDto createOrder(CreateOrderDto createOrderDto, Optional<String> email){
        logger.info("New order placed");
        Order order = orderRepository.addOrder(OrderMapper.createOrderDto_to_Order(createOrderDto, email));
        return OrderMapper.order_to_ReceiveOrderDto(order);
    }
}
