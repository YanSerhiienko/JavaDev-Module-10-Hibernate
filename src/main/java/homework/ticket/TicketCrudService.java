package homework.ticket;

import homework.client.Client;
import homework.client.ClientCrudService;
import homework.planet.Planet;
import homework.planet.PlanetCrudService;
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
        Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();
        return ticket.getId();
    }

    public Ticket getById(String id) throws SQLException {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Query<Ticket> query = session.createQuery("from Ticket where id = :id", Ticket.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }

    public List<Ticket> getAll() {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
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
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            return ticket.getId();
        }
    }

    public long delete(Ticket ticket) {
        if (!validation(ticket)) {
            return -1;
        }
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(ticket);
            transaction.commit();
            return ticket.getId();
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
