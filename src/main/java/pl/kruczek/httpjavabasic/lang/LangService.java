package pl.kruczek.httpjavabasic.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LangService {

    private final Logger LOG = LoggerFactory.getLogger(LangService.class);
    static final LangDto DEFAULT_LANG = new LangDto(UUID.randomUUID(), "Hello", "en_US");


    private LangRepository langRepository;

    public LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository langRepository) {
        this.langRepository = langRepository;
    }

    public List<LangDto> getAll() {
        return langRepository.findAll().stream().map(LangDto::fromLang).collect(Collectors.toList());
    }

    public LangDto findById(UUID id) {
        Optional<Lang> byId = langRepository.findById(id);
        return byId.isPresent() ? byId.map(LangDto::fromLang).get() : DEFAULT_LANG;
    }
}
