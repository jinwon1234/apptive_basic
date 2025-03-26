package apptive.basic.dto;

import lombok.Data;


@Data
public class ToDoDto {
    private String title;
    private String content;

    public ToDoDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
