package be.me.eurder.domain;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {


    private final static String RAW_PASSWORD = "wachtwoord";
    UserData userData = new UserData();
    Address testAddress = new Address("Teststreet 42", "Test", "Testiewestie");
    Customer testCustomer = new Customer("Test", "Customer", "abc@test.com", testAddress, "012345678", RAW_PASSWORD);
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

    @ParameterizedTest
    @ValueSource(strings = {"user@domain.com", "user@domain.co.in", "user.name@domain.com", "user_name@domain.com",
            "username@yahoo.corporate.in"})
    void assertIsValidEmail_givenValidEmails_ThenDoesNotThrowException(String input) throws Exception {
        UserValidation.assertIsValidEmail(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {".username@yahoo.com", "username@yahoo.com.", "username@yahoo..com", "username@yahoo.c",
            "username@yahoo.corporate"})
    void assertIsValidEmail_givenInvalidEmails_ThenThrowsException(String input) throws Exception {
        assertThrows(IllegalArgumentException.class, () -> UserValidation.assertIsValidEmail(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"011223344", "0488551133", "011240234", "011240234", "035454900", "092362020", "016393939"})
    void assertIsValidPhoneNumber_givenValidPhoneNumbers_thenDoesNotThrowExceptions(String input) throws Exception {
        UserValidation.assertIsValidPhone(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"01122334a", "01122334", "0124223344", "345667788", "011-22119", "04778899001"})
    void assertIsValidPhoneNumber_givenInvalidPhoneNumbers_ThenThrowsException(String input) throws Exception {
        assertThrows(IllegalArgumentException.class, () -> UserValidation.assertIsValidEmail(input));
    }

    @Test
    void assertEmailIsUnique_givenUniqueEmail_ThenDoesNotThrowException() {
        UserValidation.assertEmailIsUnique("abc.def@ghi.jkl");
    }

    @Test
    void assertEmailIsUnique_givenEmailThatAlreadyExists_ThenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> UserValidation.assertEmailIsUnique("admin@test.te"));
    }




}