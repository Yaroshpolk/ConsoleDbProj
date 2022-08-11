package by.yaroshevich;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWorker {

    private Statement statement = null;

    public DBWorker(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return this.statement;
    }

    public void runSql(String query) {
        String queryStmt = query.split(" ")[0];

        if (queryStmt.equals("INSERT") || queryStmt.equals("DELETE") || queryStmt.equals("UPDATE")) {
            try {
                this.statement.executeUpdate(query);
                System.out.println("Query completed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
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

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
