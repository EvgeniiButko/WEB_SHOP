package DataBase;

import java.sql.Connection;

public abstract class AbstractDAO {
    protected Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connectionA) { connection = connectionA; }

    public AbstractDAO(final Connection connectionA) {
        this.connection = connectionA;
    }

}
