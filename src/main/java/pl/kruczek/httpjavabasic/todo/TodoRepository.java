package pl.kruczek.httpjavabasic.todo;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.kruczek.httpjavabasic.config.DBFacade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoRepository {

    List<Todo> findAll() {
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Todo> result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();

        return result;
    }

    Optional<Todo> findById(UUID id) {
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Todo result = session.get(Todo.class, id);
        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }

    Optional<Todo> switchDoneById(UUID id){
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Todo toToggle = session.get(Todo.class, id);
        if (toToggle == null){
            return Optional.empty();
        }
        toToggle.switchDone();
        transaction.commit();
        session.close();

        return Optional.of(toToggle);
    }

    public void persist(Todo entity) {
        Session session = DBFacade.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }
}
