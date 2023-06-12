package homework.planet;

import homework.utils.DatabaseUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class PlanetCrudServiceTest {
    private PlanetCrudService service;

    @BeforeEach
    void beforeEach() {
        String connectionUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
        new DatabaseUtil().initDb(connectionUrl);
        service = new PlanetCrudService();
    }

    /*@AfterEach
    void afterEach() {
        HibernateUtil.getINSTANCE().close();
    }*/

    @Test
    void testThatClientCreatedCorrectly() throws SQLException {
        Planet original = new Planet();
        original.setId("TN1");
        original.setName("TestName");

        String createdId = service.create(original);

        Planet saved = service.getById(createdId);

        Assertions.assertEquals(original.getId(), saved.getId());
        Assertions.assertEquals(original.getName(), saved.getName());

        System.out.println("saved = " + saved);
    }

    @Test
    void setNameTest() throws SQLException {
        Planet original = new Planet();
        original.setId("TN1");
        original.setName("TestName");
        String createdId = service.create(original);
        Planet saved = service.getById(createdId);

        saved.setName("UpdatedName");

        service.setName(saved.getId(), saved.getName());

        Planet updated = service.getById(saved.getId());

        Assertions.assertEquals(saved.getName(), updated.getName());
    }

    @Test
    void deleteTest() throws SQLException {
        Planet original = new Planet();
        original.setId("TND");
        original.setName("TestNameForDelete");
        String createdId = service.create(original);
        Planet saved = service.getById(createdId);

        service.delete(saved);

        Planet deleted = service.getById(saved.getId());

        Assertions.assertNull(deleted);
    }

    @Test
    void getAllTest() {
        List<Planet> expected = expected();
        List<Planet> getAll = service.getAll();
        Assertions.assertEquals(getAll, expected);
    }

    private List<Planet> expected() {
        Planet planet1 = new Planet();
        planet1.setId("MCY1");
        planet1.setName("Mercury");
        Planet planet2 = new Planet();
        planet2.setId("VNS2");
        planet2.setName("Venus");
        Planet planet3 = new Planet();
        planet3.setId("ERH3");
        planet3.setName("Earth");
        Planet planet4 = new Planet();
        planet4.setId("MRS4");
        planet4.setName("Mars");
        Planet planet5 = new Planet();
        planet5.setId("JTR5");
        planet5.setName("Jupiter");

        return Arrays.asList(planet1, planet2, planet3, planet4, planet5);
    }
}