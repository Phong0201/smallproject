package com.smallproject.helpers;

import com.smallproject.modules.users.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHanler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> hanlerValidException(MethodArgumentNotValidException exception){

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });

        ErrorDto errorDto = new ErrorDto(
                "có vấn đề sảy ra trong quá trình kiểm tra dữ liệu !!" , errors);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }
}
