package apptive.basic.controller;


import apptive.basic.dto.ToDoDto;
import apptive.basic.exception.NotFoundToDo;
import apptive.basic.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping("/todo")
    public ResponseEntity<ToDoDto> saveToDo(@RequestBody ToDoDto toDoDto) {

        toDoService.save(toDoDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDoDto);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?> getToDo(@PathVariable Long id) {
        try {
            ToDoDto toDoDto = toDoService.findToDo(id);
            return ResponseEntity.status(HttpStatus.OK).body(toDoDto);
        }
        catch (NotFoundToDo notFoundToDo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message",notFoundToDo.getMessage()));
        }
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String,String>> deleteToDo(@PathVariable Long id) {

        if(toDoService.delete(id)) return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("message","삭제 성공"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","삭제 실패"));

    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<?> updateToDo(@PathVariable Long id, @RequestBody ToDoDto toDoDto) {

        try {
            ToDoDto updateToDo = toDoService.update(id, toDoDto);
            return ResponseEntity.status(HttpStatus.OK).body(updateToDo);
        }
        catch (NotFoundToDo notFoundToDo) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message",notFoundToDo.getMessage()));
        }
    }

}
