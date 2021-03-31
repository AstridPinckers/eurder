package be.me.eurder.domain.mock_data;

import be.me.eurder.domain.UserValidation;
import be.me.eurder.domain.pojos.user.Address;
import be.me.eurder.domain.pojos.user.Admin;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.domain.pojos.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDataTest {
    private final static String EMPTY_STRING = "";
    private final static String BLANK_STRING = " ";
    private final static Address VALID_ADDRESS = new Address("TestStreet 25", "3000", "TestCity");
    private final static String VALID_STRING = "abcdefg";
    private final static String VALID_PHONE = "0477889900";
    private final static String VALID_EMAIL = "def@test.be";
    private final static String RAW_PASSWORD = "wachtwoord";
    UserData userData = new UserData();
    Address testAddress = new Address("Teststreet 42", "Test", "Testiewestie");
    Customer testCustomer = new Customer("Test", "Customer", "abc@test.com", testAddress, "012345678",RAW_PASSWORD);
    Admin testAdmin = new Admin("Admin", "Testzerson", "admin@test.te", testAddress, "0477889900", RAW_PASSWORD);

    @BeforeEach
    void init() throws Exception {
        Field reader;
        reader = UserData.class.getDeclaredField("userList");
        reader.setAccessible(true);
        reader.set(userData, new ArrayList<>(List.of(testCustomer, testAdmin)));

        UserValidation userValidation = new UserValidation();
        Field validationReader;
        validationReader = UserValidation.class.getDeclaredField("userList");
        validationReader.setAccessible(true);
        validationReader.set(userValidation, new ArrayList<>(List.of(testCustomer, testAdmin)));
    }

    @Test
    void getUserList() {
        List<User> userList = userData.getUserList();
        assertEquals("Customer", userList.get(0).getLastName());
        assertEquals("Testzerson", userList.get(1).getLastName());
        assertEquals(2, userList.size());
    }

    @Test
    void getAdminList() {
        List<User> adminList = userData.getAdminList();
        assertEquals(1, adminList.size());
        assertEquals("Testzerson", adminList.get(0).getLastName());
    }

    @Test
    void getCustomerList() {
        List<User> customerList = userData.getCustomerList();
        assertEquals(1, customerList.size());
        assertEquals("Customer", customerList.get(0).getLastName());
    }

    @Nested
    class Add {

        @Test
        void addValidCustomer() {
            Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
            assertEquals(customer, userData.addCustomer(customer));
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING})
        void addCustomerWithInvalidFirstname(String string) throws Exception {
            Customer customer = new Customer(string, VALID_STRING, VALID_EMAIL, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("First name cannot be empty", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING})
        void addCustomerWithInvalidLastname(String string) throws Exception {
            Customer customer = new Customer(VALID_STRING, string, VALID_EMAIL, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("Last Name cannot be empty", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING, ".username@yahoo.com", "username@yahoo.com.", "username" +
                "@yahoo..com", "username@yahoo.c",
                "username@yahoo.corporate", "abc@test.com", "admin@test.te"})
        void addCustomerWithInvalidEmail(String string) throws Exception {

            Customer customer = new Customer(VALID_STRING, VALID_STRING, string, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
            assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING, "01122334a", "01122334", "0124223344", "345667788", "011" +
                "-22119", "04778899001"})
        void addCustomerWithInvalidPhoneNumber(String string) throws Exception {
            Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, VALID_ADDRESS, string, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("Invalid phone number", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING})
        void addCustomerWithInvalidAddressLine(String string) throws Exception {
            Address address = new Address(string, VALID_STRING, VALID_STRING);
            Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("Address line cannot be empty", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING})
        void addCustomerWithInvalidPostalCode(String string) throws Exception {
            Address address = new Address(VALID_STRING, string, VALID_STRING);
            Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("Postal code cannot be empty", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {EMPTY_STRING, BLANK_STRING})
        void addCustomerWithInvalidCity(String string) throws Exception {
            Address address = new Address(VALID_STRING, VALID_STRING, string);
            Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
            Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
            assertEquals("City cannot be empty", exception.getMessage());
        }

        @Nested
        class AddCustomerWithNullField {

            @Test
            void addCustomerWithNullCity() {
                Address address = new Address(VALID_STRING, VALID_STRING, null);
                Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("City cannot be empty", exception.getMessage());
            }

            @Test
            void addCustomerWithNullAddressLine() {
                Address address = new Address(null, VALID_STRING, VALID_STRING);
                Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("Address line cannot be empty", exception.getMessage());
            }
            @Test
            void addCustomerWithNullPhoneNumber() {
                Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, VALID_ADDRESS, null, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("Invalid phone number", exception.getMessage());
            }

            @Test
            void addCustomerWithNullLastName() {
                Customer customer = new Customer(VALID_STRING, null, VALID_EMAIL, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("Last Name cannot be empty", exception.getMessage());
            }

            @Test
            void addCustomerWithNullFirstName(){
                Customer customer = new Customer(null, VALID_STRING, VALID_EMAIL, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("First name cannot be empty", exception.getMessage());
            }

            @Test
            void addCustomerWithNullPostalCode() {
                Address address = new Address(VALID_STRING, null, VALID_STRING);
                Customer customer = new Customer(VALID_STRING, VALID_STRING, VALID_EMAIL, address, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class, () -> userData.addCustomer(customer));
                assertEquals("Postal code cannot be empty", exception.getMessage());
            }

            @Test
            void addCustomerWithNullEmail() {
                Customer customer = new Customer(VALID_STRING, VALID_STRING, null, VALID_ADDRESS, VALID_PHONE, RAW_PASSWORD);
                Exception exception = assertThrows(IllegalArgumentException.class,
                        () -> userData.addCustomer(customer));
                assertEquals("Invalid email address",exception.getMessage());
            }
        }
    }
    @ParameterizedTest
    @ValueSource(strings = {"admin@test.te", "abc@test.com"})
    void getUserByEmail_existingEmail(String string){
        assertNotNull(userData.getUserByEmail(string));
        assertNotNull(userData.getUserByEmail(string));
    }

    @Test
    void getUserByEmail_emailDoesNotExist(){
        assertNull(userData.getUserByEmail("doesnotexist@test.be"));
    }


}
