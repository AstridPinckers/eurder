package be.me.eurder.domain.repositories;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.Customer;
import be.me.eurder.domain.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserRepository {

    private final UserData userData;

    @Autowired
    public UserRepository(UserData userData) {
        this.userData = userData;
    }

    public User addCustomer(Customer customer){
        userData.addCustomer(customer);
        return customer;
    }

}
