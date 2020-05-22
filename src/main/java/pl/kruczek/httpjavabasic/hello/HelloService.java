package pl.kruczek.httpjavabasic.hello;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.kruczek.httpjavabasic.lang.LangDto;
import pl.kruczek.httpjavabasic.lang.LangService;

import java.util.UUID;

class HelloService {

    private final Logger LOG = LoggerFactory.getLogger(HelloService.class);

    private LangService langService;

    HelloService() {
        this(new LangService());
    }

    HelloService(LangService langService) {
        this.langService = langService;
    }

    String getGreeting(String user, UUID id) {
        String name = StringUtils.isBlank(user) ? "world" : user;
        LangDto welcomeMsg = langService.findById(id);
        return welcomeMsg.getMsg() + " " + name + "!";
    }
}
