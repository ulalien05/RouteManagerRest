package ru.volkova.springcourse.RouteManagerRest.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.volkova.springcourse.RouteManagerRest.util.exceptions.NotCreatedException;

import java.util.List;

public class ErrorUtil {

    public static void returnErrorInfo(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors  = bindingResult.getFieldErrors();
        for (FieldError error : errors){
            errorMessage.append(error.getField())
                    .append(" - ").append(error.getDefaultMessage())
                    .append("; ");
        }
        throw new NotCreatedException(errorMessage.toString());
    }
}
