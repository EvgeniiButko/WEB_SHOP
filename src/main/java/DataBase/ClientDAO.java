package DataBase;

import DataBase.DataBaseComponents.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAO extends AbstractDAO{
    private static String ADD_CLIENT = "INSERT INTO myschema.client(login,password,mail) " +
            " VALUES(?,?,?)";
    private static String DELETE_CLIENT_BY_ID = "DELETE FROM myschema.client" +
            " WHERE id = ?";
    private static String GET_CLIENT_PASSWORD_BY_LOGIN = "SELECT * FROM myschema.client" +
            " WHERE login = ?";
    private static String GET_CLIENT_BY_ID = "SELECT * FROM myschema.client" +
            " WHERE id = ?";
    private static String GET_CLIENTMAIL_BY_LOGIN = "SELECT mail FROM myschema.client" +
            " WHERE login = ?";

    public ClientDAO(Connection connectionA) {
        super(connectionA);
    }

    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                DELETE_CLIENT_BY_ID);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();

        preparedStatement.close();
    }

    public String getClientPasswordByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_CLIENT_PASSWORD_BY_LOGIN);
        preparedStatement.setString(1,login);

        ResultSet resultSet = preparedStatement.executeQuery();
        String Client_Password ="";

        while(resultSet.next()) {
            Client_Password = resultSet.getString("password");
        }

        resultSet.close();
        preparedStatement.close();
        return Client_Password;
    }

    public Client getClientByID(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Client client = getClient(resultSet);
        resultSet.close();
        preparedStatement.close();

        return client;
    }
    public Client getClient(final ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getInt("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("mail")
        );
    }

    public void addClient(String login,String password,String mail) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_CLIENT);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,password);
        preparedStatement.setString(3,mail);

        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Returns client`s mail via setting the login
     * @param login
     * @return
     * @throws SQLException
     */
    public String getClientMailByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENTMAIL_BY_LOGIN);
        preparedStatement.setString(1,login);
        String result = null;

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getString("mail");
        }
        resultSet.close();
        preparedStatement.close();

        return result;
    }
}
