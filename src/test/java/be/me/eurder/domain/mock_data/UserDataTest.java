package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.UserValidation;
import be.me.eurder.domain.pojos.Address;
import be.me.eurder.domain.pojos.Admin;
import be.me.eurder.domain.pojos.Customer;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {
    UserData userData;

    @BeforeEach
    void init() throws Exception {
        userData = new UserData();
        Field reader;
        reader = UserData.class.getDeclaredField("userList");
        reader.setAccessible(true);
        Address address = new Address("Teststreet 42", "Test","Testiewestie");
        Customer customer = new Customer("Test","Customer","abc@test.com",address,"012345678");
        Admin admin = new Admin("Admin","Testzerson","admin@test.te",address,"0477889900");
        reader.set(userData, new ArrayList<>(List.of(customer,admin)));
    }



}

/*
 public List<User> getUserList() {
        return userList;
    }

    public List<User> getAdminList() {
        return userList.stream().collect(groupingBy(User::getRole, Collectors.toList())).get("admin");
    }

    public List<User> getCustomerList() {
        return userList.stream().collect(groupingBy(User::getRole, Collectors.toList())).get("customer");
    }

    public Customer addCustomer(Customer customer){
        UserValidation.assertValidUser(customer);
        userList.add(customer);
        return customer;
    }
 */