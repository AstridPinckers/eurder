package be.me.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(value = UNAUTHORIZED )
public class InvalidCredentialsException  extends ResponseStatusException {
    public InvalidCredentialsException(String message) {
        super(UNAUTHORIZED, message);
    }
}
