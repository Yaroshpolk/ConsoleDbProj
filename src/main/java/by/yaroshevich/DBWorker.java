package by.yaroshevich;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {

    private final Statement statement;

    public DBWorker(Statement statement) {
        this.statement = statement;
    }

    private void executeUpdate(String query) {
        try {
            this.statement.executeUpdate(query);
            System.out.println("Query completed");
        } catch (SQLException err) {
            Messenger.printErrorMessage(err.getMessage());
        }
    }

    private void executeQuery(String query) {
        try {
            ResultSet resultSet = this.statement.executeQuery(query);
            ResultSetMetaData resultSetMeta = resultSet.getMetaData();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMeta.getColumnCount(); i++) {
                    System.out.println(resultSetMeta.getColumnName(i) + ": " + resultSet.getString(i));
                }
                System.out.println("________________________");
            }
            if (!resultSet.next()) {
                System.out.println("All records displayed");
            }

        } catch (SQLException err) {
            Messenger.printErrorMessage(err.getMessage());
        }
    }

    public void processTheRequest(String query) {
        if (query.isEmpty()) {
            return;
        }

        String queryStmt = query.split(" ")[0];

        if (queryStmt.equalsIgnoreCase("INSERT") || queryStmt.equalsIgnoreCase("DELETE")
                || queryStmt.equalsIgnoreCase("UPDATE")) {
            executeUpdate(query);
        } else if (queryStmt.equalsIgnoreCase("SELECT")){
            executeQuery(query);
        } else {
            System.out.println("");
        }
    }

}
