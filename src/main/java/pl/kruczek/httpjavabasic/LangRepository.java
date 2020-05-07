package pl.kruczek.httpjavabasic;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class LangRepository {

    private final Map<UUID, Lang> languages;

    LangRepository() {
        this.languages = new HashMap<>();
    }

    Optional<Lang> findById(UUID id) {
        return Optional.ofNullable(languages.get(id));
    }
}
