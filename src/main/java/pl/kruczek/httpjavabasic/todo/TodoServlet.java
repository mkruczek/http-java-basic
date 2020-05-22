package pl.kruczek.httpjavabasic.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kruczek.httpjavabasic.lang.LangService;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "todo", urlPatterns = "/api/todos/*")
public class TodoServlet extends HttpServlet {
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final Logger LOG = LoggerFactory.getLogger(TodoServlet.class);

    private TodoService service;
    private ObjectMapper mapper;

    public TodoServlet() {
        this(new TodoService(), new ObjectMapper());
    }

    TodoServlet(TodoService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("get request: {}", req.getParameterMap());
        resp.setContentType(APPLICATION_JSON);
        mapper.writeValue(resp.getOutputStream(), service.getAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getPathInfo().substring(1);
        TodoDto todoDto = service.switchDone(UUID.fromString(id));
        resp.setContentType(APPLICATION_JSON);
        mapper.writeValue(resp.getOutputStream(), todoDto);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        TodoDto todoDto = mapper.readValue(inputStream, TodoDto.class);
        TodoDto saved = service.save(todoDto);
        resp.setContentType(APPLICATION_JSON);
        mapper.writeValue(resp.getOutputStream(), saved);
    }
}
