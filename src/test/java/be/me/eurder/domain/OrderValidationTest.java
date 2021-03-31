package be.me.eurder.domain;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.ItemGroup;
import be.me.eurder.domain.pojos.Order;
import be.me.eurder.domain.pojos.Price;
import be.me.eurder.domain.pojos.user.Address;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.infrastructure.exceptions.NotFoundInDatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderValidationTest {

    String VALID_STRING = "test";
    Address testAddress = new Address("Teststreet 42", "Test", "Testiewestie");
    Customer testCustomer = new Customer("Test", "Customer", "abc@test.com", testAddress, "012345678", "wachtwoord");
    Item testItem = new Item("Test", "Testonetwothree", Price.priceInEuros(5.0), 2);
    ItemGroup testItemGroup = null;

    UserData userData = new UserData();
    ItemData itemData = new ItemData();


    @BeforeEach
    void init() throws Exception {
        Field userReader;
        userReader = UserData.class.getDeclaredField("userList");
        userReader.setAccessible(true);
        userReader.set(userData, new ArrayList<>(List.of(testCustomer)));

        Field itemReader;
        itemReader = ItemData.class.getDeclaredField("itemList");
        itemReader.setAccessible(true);
        itemReader.set(itemData, new ArrayList<>(List.of(testItem)));
        testItemGroup = new ItemGroup(testItem.getUuid(), 1);

    }

    @Test
    void assertValidOrder_OrderIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> OrderValidation.assertValidOrder(null));
        assertEquals("Order cannot be empty", exception.getMessage());
    }

    @Test
    void assertValidOrder_ValidOrder(){
        Order order = new Order(testCustomer, new HashSet<>(Set.of(testItemGroup)));
        OrderValidation.assertValidOrder(order);
    }

    @Test
    void assertValidOrder_CustomerNotInDatabase() {
        Customer customer = new Customer(VALID_STRING,VALID_STRING,"customer@Test.be",testAddress,"011223344",
                VALID_STRING);
        Order order = new Order(customer, new HashSet<>(Set.of(testItemGroup)));
        assertThrows(NotFoundInDatabaseException.class, ()-> OrderValidation.assertValidOrder(order));
    }

    @Test
    void assertValidOrder_EmptyOrderSet() {
        Order order = new Order(testCustomer,new HashSet<>());
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> OrderValidation.assertValidOrder(order));
        assertEquals("Order must contain items", exception.getMessage());
    }


    @Test
    void assertValidOrder_lessThanOneAmountOrdered() {
        ItemGroup itemGroup = new ItemGroup(testItem.getUuid(),0);
        Order order = new Order(testCustomer, new HashSet<>(Set.of(itemGroup)));
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> OrderValidation.assertValidOrder(order));
        assertEquals("The minimum order amount per item is one",exception.getMessage());
    }

    @Test
    void assertValidOrder_ItemNotInDatabase() {
        Item item = new Item(VALID_STRING,VALID_STRING,Price.priceInEuros(20),10);
        assertThrows(NotFoundInDatabaseException.class, ()-> new ItemGroup(item.getUuid(),1));
    }

}