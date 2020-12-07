package store;

import model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {

    private static final Store INSTANCE = new HbmStore();
    private final Configuration configuration;


    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf;

    private HbmStore() {
        configuration = new Configuration();
        sf = configuration.configure().buildSessionFactory();
    }


    public static Store getInstance() {
        return INSTANCE;
    }

    public <T> T wrapperOne(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void wrapperTwo(BiConsumer<Session, Item> function, Item arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Item item) {
        wrapperTwo(Session::delete, item);
    }

    public void update(Item item) {
        wrapperTwo(Session::update, item);
    }

    public Item findItemById(int id) {
       return this.wrapperOne(
               session -> session.get(Item.class, id)
       );
    }

    @Override
    public void addItem(Item item) {
            this.wrapperOne(session -> session.save(item));
    }

    public List<Item> findAll() {
        return this.wrapperOne(
                session -> session.createQuery("from Item").list()
        );
    }

    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
