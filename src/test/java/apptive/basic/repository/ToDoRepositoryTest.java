package apptive.basic.repository;

import apptive.basic.domain.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class ToDoRepositoryTest {

    @Autowired
    ToDoRepository toDoRepository;

    @Test
    void bulkError() {
        ToDo toDo1 = new ToDo("제목", "내용1");
        ToDo toDo2 = new ToDo("제목", "내용2");

        toDoRepository.save(toDo1);
        toDoRepository.save(toDo2);

        toDoRepository.updateTitle("제목", "변경된 제목");

        ToDo findToDo1 = toDoRepository.findById(toDo1.getId()).orElseThrow();
        ToDo findToDo2 = toDoRepository.findById(toDo2.getId()).orElseThrow();

        System.out.println(findToDo1.getTitle());
        System.out.println(findToDo2.getTitle());
    }
}