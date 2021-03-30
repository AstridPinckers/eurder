package be.me.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(value = SERVICE_UNAVAILABLE )
public class CouldNotEncryptPasswordException extends ResponseStatusException {

    public CouldNotEncryptPasswordException() {
        super(SERVICE_UNAVAILABLE, "Could not encrypt password");
    }
}
