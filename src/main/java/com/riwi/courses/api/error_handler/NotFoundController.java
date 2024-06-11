package com.riwi.courses.api.error_handler;

import com.riwi.courses.api.dto.error.BaseErrorResponse;
import com.riwi.courses.api.dto.error.ErrorResponse;
import com.riwi.courses.util.exceptions.IdNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundController {

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception) {

        return ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseErrorResponse handleInternalServerError(Exception exception) {
        return ErrorResponse.builder()
                .message("Check your url. "+exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
