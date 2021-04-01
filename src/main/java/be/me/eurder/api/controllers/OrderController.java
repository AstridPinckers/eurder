package be.me.eurder.api.controllers;

import be.me.eurder.service.OrderService;
import be.me.eurder.service.SecurityService;
import be.me.eurder.service.dtos.CreateItemDto;
import be.me.eurder.service.dtos.CreateOrderDto;
import be.me.eurder.service.dtos.ReceiveItemDto;
import be.me.eurder.service.dtos.ReceiveOrderDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final SecurityService securityService;

    @Autowired
    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiveOrderDto createOrder(@RequestHeader("email") Optional<String> email,
                                      @RequestHeader("password") Optional<String> rawPassword,
                                      @RequestBody CreateOrderDto createOrderDto) {
        logger.info("Attempt to create new order");
        securityService.assertValidCredentials(email, rawPassword);
        return orderService.createOrder(createOrderDto,email);
    }

}
