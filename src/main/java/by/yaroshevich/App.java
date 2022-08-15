package by.yaroshevich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        Messenger.printStartMessage();

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String str;

            DBConnector dbConnector = dbAuth(bufferedReader);

            try (Connection connection = dbConnector.getConnection();
                 Statement statement = connection.createStatement()) {

                DBWorker dbWorker = new DBWorker(statement);

                while (!(str = bufferedReader.readLine()).equals("--exit")) {
                    dbWorker.processTheRequest(str);
                }

            }

            Messenger.printExitMessage();
            System.exit(0);

        } catch (IOException err) {
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Request process error.");
            err.printStackTrace();
        }
    }

    private static DBConnector dbAuth(BufferedReader reader) throws IOException {
        System.out.print("Db URL: ");
        String url = reader.readLine();
        System.out.print("User name: ");
        String name = reader.readLine();
        System.out.print("User password: ");
        String password = reader.readLine();

        return new DBConnector(url, name, password);
    }
}
