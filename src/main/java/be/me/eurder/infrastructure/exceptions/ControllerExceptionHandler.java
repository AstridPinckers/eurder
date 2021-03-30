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
                                                     HttpServletResponse response)throws IOException{
        logger.error("Invalid argument used", ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }


}
