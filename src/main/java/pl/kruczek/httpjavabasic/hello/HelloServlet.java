package pl.kruczek.httpjavabasic.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "hello", urlPatterns = "/api/hello")
public class HelloServlet extends HttpServlet {

    private final String PARAM_USER = "user";
    private final String PARAM_ID = "id";
    private final Logger LOG = LoggerFactory.getLogger(HelloServlet.class);

    HelloService service;

    public HelloServlet() {
        this(new HelloService());
    }

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("get request: {}", req.getParameterMap());
        resp.getWriter().write(service.getGreeting(
                req.getParameter(PARAM_USER),
                req.getParameter(PARAM_ID)
        ));
    }

}
