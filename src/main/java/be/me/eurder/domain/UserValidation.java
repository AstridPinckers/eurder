package be.me.eurder.domain;

import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserValidation {

    private static List<User> userList = new UserData().getUserList();

    static Logger logger = LoggerFactory.getLogger(UserValidation.class);

    private static final String REGEX_EMAIL = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String REGEX_LANDLINE_BELGIUM = "0[0-9]{8}";
    private static final String REGEX_MOBILE_BELGIUM = "04[0-9]{8}";

    public static void assertValidUser(User user) {
        if (user == null) {
            illegalArgument("User cannot be empty");
        }
        if (user.getAddress() == null) {
            illegalArgument("Address cannot be empty");
        }
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            illegalArgument("First name cannot be empty");
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            illegalArgument("Last Name cannot be empty");
        }
        if (user.getAddress().getAddressLine() == null || user.getAddress().getAddressLine().isBlank()) {
            illegalArgument("Address line cannot be empty");
        }
        if (user.getAddress().getCity() == null || user.getAddress().getCity().isBlank()) {
            illegalArgument("City cannot be empty");
        }
        if (user.getAddress().getPostalCode() == null || user.getAddress().getPostalCode().isBlank()) {
            illegalArgument("Postal code cannot be empty");
        }
        assertIsValidEmail(user.getEmail());
        assertEmailIsUnique(user.getEmail());
        assertIsValidPhone(user.getPhoneNumber());
    }


    public static void assertIsValidEmail(String email) {
        if (email == null || !email.matches(REGEX_EMAIL)) {
            logger.error("Email address does not match the expected pattern.");
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    public static void assertIsValidPhone(String phone) {
        if (phone == null || !(phone.matches(REGEX_LANDLINE_BELGIUM) || phone.matches(REGEX_MOBILE_BELGIUM))) {
            logger.error("Phone number does not match the expected pattern.");
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public static void assertEmailIsUnique(String email) {
        if (userList.stream().map(User::getEmail).filter(s -> s.equals(email)).count() != 0) {
            logger.error("Email address already exists.");
            throw new IllegalArgumentException("Email address already exists");
        }
    }

    private static void illegalArgument(String message) {
        logger.error(message);
        throw new IllegalArgumentException(message);
    }


}
