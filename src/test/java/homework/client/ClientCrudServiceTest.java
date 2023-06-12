package homework.client;

import homework.utils.DatabaseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientCrudServiceTest {
    private ClientCrudService service;

    @BeforeEach
    void beforeEach() {
        String connectionUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        new DatabaseUtil().initDb(connectionUrl);
        service = new ClientCrudService();
    }

    /*@AfterEach
    void afterEach() {
        HibernateUtil.getINSTANCE().close();
    }*/

    @Test
    void testThatClientCreatedCorrectly() throws SQLException {
        Client original = new Client();
        original.setName("Test Name");

        long createdId = service.create(original);

        Client saved = service.getById(createdId);

        Assertions.assertEquals(original.getName(), saved.getName());

        System.out.println("saved = " + saved);
    }

    @Test
    void setNameTest() throws SQLException {
        Client original = new Client();
        original.setName("Test Name");
        long createdId = service.create(original);
        Client saved = service.getById(createdId);

        saved.setName("Updated Name");

        service.setName(saved.getId(), saved.getName());

        Client updated = service.getById(saved.getId());

        Assertions.assertEquals(saved.getName(), updated.getName());
    }

    @Test
    void deleteTest() throws SQLException {
        Client original = new Client();
        original.setName("Test Name");
        long createdId = service.create(original);
        Client saved = service.getById(createdId);

        service.delete(saved);

        Client deleted = service.getById(saved.getId());

        Assertions.assertNull(deleted);
    }

    @Test
    void getAllTest() {
        List<Client> expected = expected();
        List<Client> getAll = service.getAll();
        Assertions.assertEquals(getAll, expected);
    }

    private List<Client> expected() {
        Client client1 = new Client();
        client1.setId(1);
        client1.setName("Max Pain");
        Client client2 = new Client();
        client2.setId(2);
        client2.setName("Jango Kek");
        Client client3 = new Client();
        client3.setId(3);
        client3.setName("Optimus Priem");
        Client client4 = new Client();
        client4.setId(4);
        client4.setName("Boris Bruda");
        Client client5 = new Client();
        client5.setId(5);
        client5.setName("Arnold Hey");
        Client client6 = new Client();
        client6.setId(6);
        client6.setName("Nicolas Cgae");
        Client client7 = new Client();
        client7.setId(7);
        client7.setName("John Snow");
        Client client8 = new Client();
        client8.setId(8);
        client8.setName("Kandruk Lambar");
        Client client9 = new Client();
        client9.setId(9);
        client9.setName("Tony Versetti");
        Client client10 = new Client();
        client10.setId(10);
        client10.setName("Scotty Doesntknow");
        return Arrays.asList(client1, client2, client3, client4, client5, client6, client7, client8, client9, client10);
    }
}