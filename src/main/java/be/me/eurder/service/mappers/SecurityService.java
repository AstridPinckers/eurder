package be.me.eurder.service.mappers;

import be.me.eurder.domain.UserValidation;
import be.me.eurder.domain.pojos.user.*;
import be.me.eurder.infrastructure.PassWordEncryption;
import be.me.eurder.infrastructure.exceptions.AdminPermissionException;
import be.me.eurder.infrastructure.exceptions.InvalidCredentialsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static be.me.eurder.domain.mock_data.UserData.getUserByEmail;

@Service
public class SecurityService {
     Logger logger = LoggerFactory.getLogger(UserValidation.class);


    public  void assertValidCredentialsForAdmin(Optional<String> email, Optional<String> rawPassword) {
        assertValidCredentials(email, rawPassword);
        User user = getUserByEmail(email.orElse(null));
        if (!Admin.isAdmin(user)) {
            throw new AdminPermissionException(getUserByEmail(email.orElse(null)).getUuid().toString());
        }
    }

    public  void assertValidCredentials(Optional<String> email, Optional<String> rawPassword) {
        if (email.isEmpty() || rawPassword.isEmpty()) {
            logger.error("Email and password are needed to validate");
            throw new IllegalArgumentException("Email and password are needed to validate");
        }
              User user = getUserByEmail(email.get());
        if (user == null) {
            throw new InvalidCredentialsException("Email not found");
        }
        String encryptedPasswordToCheck = PassWordEncryption.generateEncryptedPassword(rawPassword.get(),
                user.getSalt());
        if (!encryptedPasswordToCheck.equals(user.getEncryptedPassword())) {
            throw new InvalidCredentialsException(user.getUuid().toString());
        }
    }

}
