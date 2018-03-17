package DataBase;

import DataBase.DataBaseComponents.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDAO extends AbstractDAO{
    private static String GET_ALL = "SELECT * FROM myschema.order";
    private static String GET_ORDERS_BY_USER = "SELECT * FROM myschema.order WHERE login = ?";
    private static String ADD_ORDER = "INSERT INTO myschema.order(login,productid,phonenumb) " +
            "VALUES (?,?,?)";
    private static String GET_ALL_NUMBERS_BY_PRODUCTID = "SELECT phonenumber FROM myschema.order " +
            "WHERE productid = ?";

    public OrderDAO(Connection connectionA) {
        super(connectionA);
    }

    public List<String> getAllNumbersByProduct(int prodID) throws SQLException {
        List<String> list = new LinkedList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_NUMBERS_BY_PRODUCTID);
        preparedStatement.setInt(1,prodID);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(resultSet.getString("productid"));
        }
        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public void addOrder(String login, int productid, String phonenumb) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
        preparedStatement.setInt(2,productid);
        preparedStatement.setString(1,login);
        preparedStatement.setString(3,phonenumb);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<Order> getOrderByLogin(String login) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS_BY_USER);
        preparedStatement.setString(1,login);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(new Order(
                    resultSet.getInt("id"),
                    resultSet.getString("phonenumb"),
                    resultSet.getString("login"),
                    resultSet.getInt("productid")
            ));
        }

        resultSet.close();
        preparedStatement.close();

        return list;
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            list.add(new Order(
                    resultSet.getInt("id"),
                    resultSet.getString("phonenumb"),
                    resultSet.getString("login"),
                    resultSet.getInt("productid")
            ));
        }

        preparedStatement.close();
        return list;
    }
}
