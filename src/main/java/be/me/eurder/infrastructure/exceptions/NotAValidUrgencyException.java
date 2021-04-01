package be.me.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotAValidUrgencyException extends ResponseStatusException {

    public NotAValidUrgencyException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
