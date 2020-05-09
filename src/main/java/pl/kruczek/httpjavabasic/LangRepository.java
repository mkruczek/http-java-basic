package pl.kruczek.httpjavabasic;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.UUID;

class LangRepository {

    Optional<Lang> findById(UUID id) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Lang result = session.get(Lang.class, id);
        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }
}
