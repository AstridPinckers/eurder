package be.me.eurder.infrastructure;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PassWordEncryptionTest {

    @Test
    void generateEncryptedPassword(){
        byte[] salt = PassWordEncryption.getSalt();
        String rawPassword = "test";
        String password = PassWordEncryption.generateEncryptedPassword(rawPassword,salt);
        assertEquals(password,PassWordEncryption.generateEncryptedPassword(rawPassword,salt));
    }

}