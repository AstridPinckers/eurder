package be.me.eurder.service.mappers;

import be.me.eurder.domain.pojos.user.Address;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import be.me.eurder.service.dtos.*;

public class UserMapper {

    public static Customer createUserDto_to_Customer(CreateUserDto createUserDto){
        Address address = new Address(createUserDto.getAddressLine(),createUserDto.getPostalCode(),
                createUserDto.getCity());
        return new Customer(createUserDto.getFirstName(),createUserDto.getLastName(), createUserDto.getEmail(),
                address, createUserDto.getPhoneNumber(), createUserDto.getRawPassword());
    }

    public static ReceiveUserDto user_to_ReceiveUserDto(User user){
        return new ReceiveUserDto()
                .setAddress(user.getAddress())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber())
                .setRole(user.getRole());
    }
}


