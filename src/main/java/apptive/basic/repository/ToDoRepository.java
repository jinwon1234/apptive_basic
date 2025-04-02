package apptive.basic.repository;

import apptive.basic.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
