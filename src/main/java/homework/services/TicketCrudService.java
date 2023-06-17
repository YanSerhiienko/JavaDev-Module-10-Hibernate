package homework.services;

import homework.entity.Client;
import homework.entity.Planet;
import homework.entity.Ticket;
import homework.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class TicketCrudService {
    public long create(Ticket ticket) {
        if (!validation(ticket)) {
            return -1;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ticket.getId();
    }

    public Ticket getById(String id) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Ticket> query = session.createQuery("from Ticket where id = :id", Ticket.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }

    public List<Ticket> getAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
            transaction.commit();
            return tickets;
        }
    }

    public long update(Ticket ticket) {
        if (!validation(ticket)) {
            return -1;
        }
        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return ticket.getId();
    }

    public void delete(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private boolean validation (Ticket ticket) {
        if (ticket == null || ticket.getClient() == null || ticket.getFrom() == null || ticket.getTo() ==  null) {
            return false;
        }
        Client client = new ClientCrudService().getById(ticket.getClient().getId());
        if (client == null) {
            return false;
        }
        Planet fromPlanet = new PlanetCrudService().getById(ticket.getFrom().getId());
        if (fromPlanet == null) {
            return false;
        }
        Planet toPlanet = new PlanetCrudService().getById(ticket.getTo().getId());
        if (toPlanet== null) {
            return false;
        }
        return true;
    }
}
