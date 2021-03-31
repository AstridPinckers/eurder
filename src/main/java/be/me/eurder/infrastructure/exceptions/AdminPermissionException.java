package be.me.eurder.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AdminPermissionException extends ResponseStatusException {
    public AdminPermissionException(String message) {
        super(HttpStatus.FORBIDDEN, "userId: "+message);
    }
}
