package be.me.eurder.infrastructure.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    protected void IllegalArgumentExceptionValidator(IllegalArgumentException ex,
                                                     HttpServletResponse response) throws IOException {
        logger.error("Invalid argument used", ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(CouldNotEncryptPasswordException.class)
    protected void CouldNotEncryptPasswordExceptionValidator(CouldNotEncryptPasswordException exception,
                                                             HttpServletResponse response) throws IOException {
        logger.error("Could not find encryption method", exception);
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    protected void InvalidCredentialsExceptionValidator(InvalidCredentialsException exception,
                                                        HttpServletResponse response) throws IOException {
        logger.error("Failed authentication attempt: userid: " + exception.getMessage(), exception);
        response.sendError(exception.getStatus().value(), "Invalid email and password combination");
    }

    @ExceptionHandler(AdminPermissionException.class)
    protected void AdminPermissionExceptionValidator(AdminPermissionException exception,
                                                     HttpServletResponse response) throws IOException{
        logger.error("Attempt at unauthorised action: " + exception.getMessage(),exception);
        response.sendError(exception.getStatus().value(), "Only admins are allowed to do this");
    }

    @ExceptionHandler(NotFoundInDatabaseException.class)
    protected void NotFoundInDatabaseExceptionValidator(NotFoundInDatabaseException exception,
                                                        HttpServletResponse response) throws IOException{
        logger.error(exception.getMessage(), exception);
        response.sendError(exception.getStatus().value(), exception.getMessage());
    }


}
