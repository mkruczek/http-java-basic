package pl.kruczek.httpjavabasic.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TodoService {

    private final Logger LOG = LoggerFactory.getLogger(TodoService.class);
    static final TodoDto DEFAULT_TODO = new TodoDto(UUID.randomUUID(), "All done :D!", true);


    private TodoRepository todoRepository;

    public TodoService() {
        this(new TodoRepository());
    }

    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoDto> getAll() {
        return todoRepository.findAll().stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    public TodoDto findById(UUID id) {
        Optional<Todo> byId = todoRepository.findById(id);
        return byId.isPresent() ? byId.map(TodoDto::fromEntity).get() : DEFAULT_TODO;
    }

    public TodoDto switchDone(UUID id){
        Optional<Todo> todo = todoRepository.switchDoneById(id);
        return todo.map(TodoDto::fromEntity).orElseThrow(()->new MissingResourceException("Lack of object : ", "Todo.class", id.toString()));
    }

    public TodoDto save(TodoDto todoDto) {
        todoDto.generateId();
        Todo entity = TodoDto.toEntity(todoDto);
        todoRepository.persist(entity);
        return todoDto;
    }
}
