package hxw.test.springbootunittesting.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResult errorResult = new ErrorResult();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorResult.getFieldErrors().add(
                new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        });

        return errorResult;
    }
}
