package DataBase.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Pattern SingleTon
public class ConnectionGetter {

    private static DataSource dataSource;
    private static List<Connection> connection = new ArrayList<>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        InitialContext initContext;
        try {
            initContext = new InitialContext();
            dataSource = (DataSource) initContext.
                    lookup("java:comp/env/jdbc/My");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        for (int i = 0; i < connection.size(); i++) {
            if(connection.get(i).isClosed())return connection.get(i);
        }
        connection.add(dataSource.getConnection());
        return connection.get(connection.size()-1);
    }
}
