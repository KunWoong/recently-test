package kwy.taxi.allocation.config;

import kwy.taxi.allocation.utils.ErrorResponse;
import kwy.taxi.allocation.utils.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomError(CustomException ex){
        return ErrorResponse.toResponseEntity(ex.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public String otherException(Exception ex){
        return ex.getMessage();
    }
}
