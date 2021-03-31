package be.me.eurder.service.mappers;

import be.me.eurder.domain.UserValidation;
import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.user.Address;
import be.me.eurder.domain.pojos.user.Admin;
import be.me.eurder.domain.pojos.user.Customer;
import be.me.eurder.infrastructure.exceptions.AdminPermissionException;
import be.me.eurder.infrastructure.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SecurityServiceTest {

    private final static String RAW_PASSWORD = "wachtwoord";

    SecurityService securityService = new SecurityService();


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
    @ValueSource(strings = {"abc@test.com", "admin@test.te"})
    void assertValidCredentials_givenValidCredentials(String string) {
        securityService.assertValidCredentials(Optional.ofNullable(string), Optional.ofNullable(RAW_PASSWORD));
        securityService.assertValidCredentials(Optional.ofNullable(string), Optional.ofNullable(RAW_PASSWORD));
    }

    @Test
    void assertValidCredentials_givenUnknownEmail() {
        assertThrows(InvalidCredentialsException.class,
                () -> securityService.assertValidCredentials(Optional.ofNullable("doesNotExist@email.com"),
                        Optional.ofNullable("nope")));
    }

    @Test
    void assertValidCredentials_givenMismatchBetweenEmailAndPassword() {
        assertThrows(InvalidCredentialsException.class,
                () -> securityService.assertValidCredentials(Optional.ofNullable("abc@test.com"),
                        Optional.ofNullable("nope")));
    }

    @Test
    void assertValidCredentialsForAdmin_givenValidButNotAdmin() {
        assertThrows(AdminPermissionException.class,
                () -> securityService.assertValidCredentialsForAdmin(Optional.ofNullable("abc@test.com"),
                        Optional.ofNullable(RAW_PASSWORD)));
    }

    @Test
    void assertValidCredentialsForAdmin_givenValidAndAdmin() {
        securityService.assertValidCredentialsForAdmin(Optional.ofNullable("admin@test.te"),
                Optional.ofNullable(RAW_PASSWORD));
    }

    @Test
    void assertValidCredentialsForAdmin_givenUnknownEmail() {
        assertThrows(InvalidCredentialsException.class,
                () -> securityService.assertValidCredentialsForAdmin(Optional.ofNullable("doesNotExist@email.com"),
                        Optional.ofNullable("nope")));
    }

    @Test
    void assertValidCredentialsForAdmin_givenMismatchBetweenEmailAndPassword() {
        assertThrows(InvalidCredentialsException.class,
                () -> securityService.assertValidCredentialsForAdmin(Optional.ofNullable("admin@test.te"),
                        Optional.ofNullable("nope")));
    }

}