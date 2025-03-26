package apptive.basic.service;

import apptive.basic.domain.ToDo;
import apptive.basic.dto.ToDoDto;
import apptive.basic.exception.NotFoundToDo;
import apptive.basic.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public void save(ToDoDto toDoDto) {
        ToDo toDo = new ToDo(toDoDto.getTitle(), toDoDto.getContent());
        toDoRepository.save(toDo);
    }

    public ToDoDto findToDo(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);

        if (toDo.isPresent()) {
            return new ToDoDto(toDo.get().getTitle(), toDo.get().getContent());
        }
        else throw new NotFoundToDo("존재하는 toDo가 없습니다.");
    }

    public boolean delete(Long id) {
        Optional<ToDo> toDo = toDoRepository.findById(id);

        if (toDo.isPresent()) {
            toDoRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public ToDoDto update(Long id, ToDoDto toDoDto) {
        Optional<ToDo> byId = toDoRepository.findById(id);

        if(byId.isPresent()) {
            ToDo toDo = byId.get();
            toDo.changeTitle(toDoDto.getTitle());
            toDo.changeContent(toDoDto.getContent());
            return new ToDoDto(toDo.getTitle(), toDo.getContent());
        }
        else throw new NotFoundToDo("존재하는 toDo가 없습니다.");
    }

}
