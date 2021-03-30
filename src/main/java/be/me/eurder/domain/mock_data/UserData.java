package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.UserValidation;
import be.me.eurder.domain.pojos.User.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class UserData {
    private static List<User> userList = new ArrayList<>(List.of(
            new Customer("Customer", "Test", "customer.test@testing.com",new Address("Testingstreet 5","4444",
                    "TestCity"),"0474112233", "wachtwoord"),
            new Admin("Admin", "Test", "admin.test@testily.co.uk",new Address("Testingstreet 5","4444",
                    "TestCity"),"011223344", "wachtwoord")
    ));

    public static List<User> getUserList() {
        return userList;
    }

    public static List<User> getAdminList() {
        return userList.stream().collect(groupingBy(User::getRole, Collectors.toList())).get("admin");
    }

    public static List<User> getCustomerList() {
        return userList.stream().collect(groupingBy(User::getRole, Collectors.toList())).get("customer");
    }

    public static Customer addCustomer(Customer customer){
        UserValidation.assertValidUser(customer);
        userList.add(customer);
        return customer;
    }
}
