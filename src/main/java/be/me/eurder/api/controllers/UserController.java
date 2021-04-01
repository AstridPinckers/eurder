package be.me.eurder.api.controllers;

import be.me.eurder.service.SecurityService;
import be.me.eurder.service.UserService;
import be.me.eurder.service.dtos.CreateUserDto;
import be.me.eurder.service.dtos.ReceiveUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ReceiveUserDto> getAllUsers(@RequestHeader("email") Optional<String> email,
                                            @RequestHeader("password") Optional<String> rawPassword){
        logger.info("User list requested");
        securityService.assertValidCredentialsForAdmin(email,rawPassword);
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{email}", produces = "application/json")
    public ReceiveUserDto getUserByEmail(@RequestHeader("email") Optional<String> adminEmail,
                                         @RequestHeader("password") Optional<String> rawPassword,
                                         @PathVariable String email) {
        logger.info("A user is looked up");
        securityService.assertValidCredentialsForAdmin(adminEmail,rawPassword);
        return userService.getUserByEmail(email);
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiveUserDto createMember(@RequestBody CreateUserDto createCustomerDto) {
        logger.info("Attempt to create new customer");
        return userService.createCustomer(createCustomerDto);
    }




}
