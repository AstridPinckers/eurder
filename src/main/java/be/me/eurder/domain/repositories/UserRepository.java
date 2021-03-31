package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import org.springframework.stereotype.Component;


@Component
public class UserRepository {


    public User addCustomer(Customer customer){
        UserData.addCustomer(customer);
        return customer;
    }

}
