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
    public ResponseEntity<ToDoDto> getToDo(@PathVariable Long id) {
        ToDoDto toDoDto = toDoService.findToDo(id);
        return ResponseEntity.status(HttpStatus.OK).body(toDoDto);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Map<String,String>> deleteToDo(@PathVariable Long id) {
        toDoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","삭제 성공"));
    }

    @PatchMapping("/todo/{id}")
    public ResponseEntity<ToDoDto> updateToDo(@PathVariable Long id, @RequestBody ToDoDto toDoDto) {
        ToDoDto updateToDo = toDoService.update(id, toDoDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateToDo);
    }

}
