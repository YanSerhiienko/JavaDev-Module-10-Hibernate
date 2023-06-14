package homework.planet;

import homework.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlanetCrudService {
    public String create(Planet planet) {
        if (planet == null) {
            return "";
        }
        Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
        return planet.getId();
    }

    public Planet getById(String id) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Query<Planet> query = session.createQuery("from Planet where id = :id", Planet.class);
            query.setParameter("id", id);
            return query.stream().findFirst().orElse(null);
        }
    }

    public List<Planet> getAll() {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Planet> planets = session.createQuery("from Planet", Planet.class).list();
            transaction.commit();
            return planets;
        }
    }

    public void setName(String id, String name) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            planet.setName(name);
            session.persist(planet);
            transaction.commit();
        }
    }

    public String delete(Planet planet) {
        if (planet == null) {
            return "";
        }
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
            return planet.getId();
        }
    }
}
