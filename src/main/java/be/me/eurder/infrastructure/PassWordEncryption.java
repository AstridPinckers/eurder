package be.me.eurder.infrastructure;

import be.me.eurder.infrastructure.exceptions.CouldNotEncryptPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public class PassWordEncryption {

    static Logger logger = LoggerFactory.getLogger(PassWordEncryption.class);
    private final static byte[] PEPPER = getSalt();

    public static String generateEncryptedPassword(String rawPassword, byte[] salt) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt);
            messageDigest.update(PEPPER);
            byte[] bytes = messageDigest.digest(rawPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("Could not encrypt password", noSuchAlgorithmException);
            throw new CouldNotEncryptPasswordException();
        }
    }


    public static byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            logger.error("Could not create salt", noSuchAlgorithmException);
            throw new CouldNotEncryptPasswordException();
        }
    }
}
