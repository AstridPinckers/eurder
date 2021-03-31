package be.me.eurder.service;

import be.me.eurder.domain.pojos.user.User;
import be.me.eurder.domain.repositories.UserRepository;
import be.me.eurder.service.dtos.CreateUserDto;
import be.me.eurder.service.dtos.ReceiveUserDto;
import be.me.eurder.service.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ReceiveUserDto createCustomer(CreateUserDto createUserDto){
        logger.info("New Customer registered");
        User user = userRepository.addCustomer(UserMapper.createUserDto_to_Customer(createUserDto));
        return UserMapper.user_to_ReceiveUserDto(user);
    }
}
