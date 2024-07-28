package com.emamagic.taskema.exception;

import com.emamagic.taskema.model.dto.response.ApiResponse;
import com.emamagic.taskema.model.dto.response.RichError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Void> handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        return  ApiResponse.<Void>builder()
                .hasError(true)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(errors.toString())
                .build();
    }

    @ExceptionHandler(RichError.class)
    public ApiResponse<Void> handleRichException(RichError exception) {
        return ApiResponse.<Void>builder()
                .hasError(true)
                .message(exception.getMessage())
                .httpStatus(exception.getHttpStatus())
                .errors(Collections.singletonList(exception))
                .build();

    }

    @ExceptionHandler
    public ResponseEntity<String> handleOtherException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
