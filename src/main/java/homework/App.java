package homework;

import homework.entity.Client;
import homework.services.ClientCrudService;
import homework.utils.DatabaseUtil;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:h2:./homework_files/homework10";
        new DatabaseUtil().initDb(connectionUrl);
        ClientCrudService service = new ClientCrudService();
        Client client = new Client();
        client.setName("New Test Client " + LocalDateTime.now().getNano());
        System.out.println("Created client: " + client);
        long newId = service.create(client);
        System.out.println("New client id: " + newId);
        service.deleteById(newId);
    }
}
