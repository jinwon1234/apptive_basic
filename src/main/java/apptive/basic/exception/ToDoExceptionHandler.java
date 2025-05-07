package apptive.basic.exception;

import apptive.basic.exception.error.ApiErrorResponse;
import apptive.basic.exception.error.FieldErrorResponse;
import apptive.basic.exception.error.GlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ToDoExceptionHandler {

    @ExceptionHandler(NotFoundToDo.class)
    public ResponseEntity<Map<String,String>> notFoundToDoHandler(NotFoundToDo ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidHandler(MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> fields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new FieldErrorResponse(error.getField(), error.getDefaultMessage()))
                .toList();

        List<GlobalErrorResponse> globals = ex.getBindingResult().getGlobalErrors().
                stream()
                .map(error -> new GlobalErrorResponse(error.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse(fields, globals));
    }

}
