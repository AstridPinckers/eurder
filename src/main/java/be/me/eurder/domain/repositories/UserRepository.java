package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserRepository {


    public User addCustomer(Customer customer){
        return  UserData.addCustomer(customer);
    }

    public List<User> getAllUsers(){
        return UserData.getUserList();
    }

    public User getUserByEmail(String email){
        return UserData.getUserByEmail(email);
    }

}
