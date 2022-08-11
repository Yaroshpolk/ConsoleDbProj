package by.yaroshevich;

import java.io.*;
import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        Messenger.printStartMessage();

        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str;

            System.out.print("Db URL: ");
            String url = bufferedReader.readLine();
            System.out.print("User name: ");
            String name = bufferedReader.readLine();
            System.out.print("User password: ");
            String password = bufferedReader.readLine();

            DBConnector dbConnector = new DBConnector(url, name, password);
            Connection connection = dbConnector.getConnection();

            Statement statement = connection.createStatement();
            DBWorker dbWorker = new DBWorker(statement);

            while (!(str = bufferedReader.readLine()).equals("--exit")) {
                dbWorker.runSql(str);
            }

            statement.close();
            connection.close();
            Messenger.printExitMessage();
            System.exit(0);

        } catch (IOException err) {
            err.printStackTrace();
        } catch (SQLException err) {
            System.out.println("Ошибка выполнения запроса.");
            err.printStackTrace();
        }
    }
}
