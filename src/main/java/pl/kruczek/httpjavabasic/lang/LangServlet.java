package pl.kruczek.httpjavabasic.lang;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "lang", urlPatterns = "/api/langs")
public class LangServlet extends HttpServlet {
    private static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    private final Logger LOG = LoggerFactory.getLogger(LangServlet.class);

    private LangService service;
    private ObjectMapper mapper;

    public LangServlet() {
        this(new LangService(), new ObjectMapper());
    }

    LangServlet(LangService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("get request: {}", req.getParameterMap());
        resp.setContentType(APPLICATION_JSON);
        mapper.writeValue(resp.getOutputStream(), service.getAll());
    }

}
