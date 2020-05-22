package pl.kruczek.httpjavabasic.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class TodoDto {

    UUID id;
    String content;
    boolean done;

    public TodoDto() {
    }

    static TodoDto fromEntity(Todo entity) {
        return TodoDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .done(entity.isDone())
                .build();
    }

    static Todo toEntity(TodoDto dto ){
        return Todo.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .done(dto.isDone())
                .build();
    }

    void generateId(){
        this.id = UUID.randomUUID();
    }
}
