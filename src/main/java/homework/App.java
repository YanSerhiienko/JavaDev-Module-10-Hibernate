package homework;

import homework.utils.DatabaseUtil;

public class App {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:h2:./homework_files/homework10";
        new DatabaseUtil().initDb(connectionUrl);
    }
}
