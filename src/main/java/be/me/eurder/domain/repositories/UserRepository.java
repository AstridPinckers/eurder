package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.User.Customer;
import be.me.eurder.domain.pojos.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserRepository {


    public User addCustomer(Customer customer){
        UserData.addCustomer(customer);
        return customer;
    }

}
