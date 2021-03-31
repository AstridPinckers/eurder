package be.me.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundInDatabaseException extends ResponseStatusException {
    public NotFoundInDatabaseException( String reason) {
        super(HttpStatus.NOT_FOUND, "The "+reason+" was not found in the database");
    }
}
