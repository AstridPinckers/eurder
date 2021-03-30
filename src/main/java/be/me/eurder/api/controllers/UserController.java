package be.me.eurder.api.controllers;

import be.me.eurder.service.UserService;
import be.me.eurder.service.dtos.CreateUserDto;
import be.me.eurder.service.dtos.ReceiveUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiveUserDto createMember(@RequestBody CreateUserDto createCustomerDto) {
        logger.info("Attempt to create new customer");
        return userService.createCustomer(createCustomerDto);
    }


}
