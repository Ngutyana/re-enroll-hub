package za.ac.cput.reenrollhub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import za.ac.cput.reenrollhub.domain.dto.ErrorResponse;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    private ErrorResponse userNotFound(RuntimeException e) {
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

}
