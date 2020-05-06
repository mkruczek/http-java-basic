package pl.kruczek.httpjavabasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "hello", urlPatterns = "/api/hello")
public class HelloServlet extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("user") != null ? req.getParameter("user") : "world";
        LOG.info("get request: {}", req);
        resp.getWriter().write("hello " + user + "!");
    }

}
