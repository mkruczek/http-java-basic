package pl.kruczek.httpjavabasic.lang;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.kruczek.httpjavabasic.config.DBFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class LangRepository {

    List<Lang> findAll() {
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Lang> result = session.createQuery("from Lang", Lang.class).list();
        transaction.commit();
        session.close();

        return result;
    }

    Optional<Lang> findById(UUID id) {
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Lang result = session.get(Lang.class, id);
        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }
}
