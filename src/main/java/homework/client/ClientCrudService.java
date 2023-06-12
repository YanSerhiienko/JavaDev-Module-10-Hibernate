package homework.client;

import homework.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ClientCrudService {
    public long create(Client client) {
        Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
        return client.getId();
    }

    public Client getById(long id) throws SQLException {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Query<Client> query = session.createQuery("from Client where id = :id", Client.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }

    public List<Client> getAll() {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Client> clients = session.createQuery("from Client", Client.class).list();
            transaction.commit();
            return clients;
        }
    }

    public void setName(long id, String name) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(name);
            session.persist(client);
            transaction.commit();
        }
    }

    public void delete(Client client) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(client);
            transaction.commit();
        }
    }

    /*public void deleteById(long id) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("delete from Client c where c.id = :id", Client.class);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }*/
}
