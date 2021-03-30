package be.me.eurder.domain;

import be.me.eurder.infrastructure.exceptions.CouldNotEncryptPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PassWordEncryption {

    static Logger logger = LoggerFactory.getLogger(PassWordEncryption.class);
    private final static String PEPPER = "bibbity, bobbity, boo";

    public static String generateEncryptedPassword(String rawPassword, UUID salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            return messageDigest.digest((rawPassword + salt+PEPPER).getBytes(StandardCharsets.UTF_8)).toString();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("Could not encrypt password", noSuchAlgorithmException);
            throw new CouldNotEncryptPasswordException();
        }
    }
}
