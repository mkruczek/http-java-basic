package pl.kruczek.httpjavabasic;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

class HelloService {

    private final Logger LOG = LoggerFactory.getLogger(HelloService.class);
    static final Lang DEFAULT_LANG = new Lang(UUID.randomUUID(), "Hello", "en_US");

    private LangRepository langRepository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    String getGreeting(String user, String langId) {
        UUID id = getUUID(langId);
        String name = StringUtils.isBlank(user) ? "world" : user;
        Lang welcomeMsg = langRepository.findById(id).orElse(DEFAULT_LANG);
        return welcomeMsg.getMsg() + " " + name + "!";
    }

    private UUID getUUID(String value) {
        if (value != null) {
            try {
                return UUID.fromString(value);
            } catch (IllegalArgumentException ex) {
                //do nothing
                LOG.warn("Return default langId for input: {}.", value);
            }
        }
        return DEFAULT_LANG.getId();
    }
}