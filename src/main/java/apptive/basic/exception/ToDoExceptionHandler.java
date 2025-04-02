package apptive.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ToDoExceptionHandler {

    @ExceptionHandler(NotFoundToDo.class)
    public ResponseEntity<Map<String,String>> notFoundToDoHandler(NotFoundToDo ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }
}
