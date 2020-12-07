package store;

import model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.function.Function;

public class HbmStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmStore.class.getName());
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

    public <T> T tx(final Function<Session, T> command) {
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

    @Override
    public boolean setStatus(int id, boolean status) {
        Session session = sf.openSession();
        session.beginTransaction();
        boolean successful = false;
        try {
            Item itemToUpdate = session.get(Item.class, id);
            itemToUpdate.setDone(status);
            session.update(itemToUpdate);
            session.getTransaction().commit();
            successful = true;
            LOG.error("Changing status is done");
        } catch (HibernateException e) {
            LOG.error("Changing status went wrong", e);
        } finally {
            session.close();
        }
        return successful;
    }

    @Override
    public boolean delete(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        boolean successful = false;
        try {
            session.delete(session.get(Item.class, item.getId()));
            session.getTransaction().commit();
            successful = true;
        } catch (HibernateException e) {
            LOG.error("Deleting went wrong", e);
        } finally {
            session.close();
        }
        return successful;
    }

    @Override
    public void addItem(Item item) {
            this.tx(session -> session.save(item));
    }

    public List<Item> findAll() {
        return this.tx(
                session -> {
                    List items = session.createQuery("from Item").list();
                    return items;
                }
        );
    }

    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
