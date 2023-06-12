package homework.planet;

import homework.client.Client;
import homework.client.ClientCrudService;
import homework.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class PlanetCrudService {
    /*    createSt = connection.prepareStatement("INSERT INTO human (name, birthday, gender) VALUES (?, ?, ?)");
    getByIdSt = connection.prepareStatement("SELECT name, birthday, gender FROM human WHERE id = ?");
    selectMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxId FROM human");
    getAllSt = connection.prepareStatement("SELECT id, name, gender, birthday FROM human");
    updateSt = connection.prepareStatement("UPDATE human SET name = ?, birthday = ?, gender = ? WHERE id = ?");
    deleteByIdSt = connection.prepareStatement("DELETE FROM human WHERE id = ?");
    existsByIdSt = connection.prepareStatement("SELECT count(*) > 0 AS humanExists FROM human WHERE id = ? ");
    clearSt = connection.prepareStatement("DELETE FROM human");
    searchSt = connection.prepareStatement("SELECT id, name, gender, birthday FROM human WHERE name LIKE ?");*/

    public static void main(String[] args) throws SQLException {
        ClientCrudService service = new ClientCrudService();

        Client client1 = new Client();
        client1.setName("Who IsThis");
        long l = service.create(client1);
        System.out.println("created id = " + l);

//        service.setName(11, "New Name");
//        Client byId1 = service.getById(11);
//        System.out.println("byId = " + byId1);

        //service.deleteById(11);
        //Client byId2 = service.getById(11);
        //System.out.println("byId = " + byId2);


//        Client byId = service.getById(11);
//        service.delete(byId);

//        List<Client> all = service.getAll();
//        System.out.println("all = " + all);


    }

    public String create(Planet planet) {
        Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(planet);
        transaction.commit();
        session.close();
        return planet.getId();
    }

    public Planet getById(String id) throws SQLException {
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

    public void delete(Planet planet) {
        try (Session session = HibernateUtil.getINSTANCE().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(planet);
            transaction.commit();
        }
    }
}
