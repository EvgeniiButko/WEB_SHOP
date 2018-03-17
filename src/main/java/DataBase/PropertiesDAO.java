package DataBase;

import DataBase.DataBaseComponents.PropertiesLC;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PropertiesDAO extends AbstractDAO {
    private static String GET_CARDNUMB_BY_LOGIN = "SELECT * FROM " +
            "myschema.prperties WHERE login = ?";
    private static String GET_LOGIN_BY_CARDNUMB = "SELECT login FROM "+
            "myschema.prperties WHERE cardnumb = ?";
    private static String GET_ALL = "SELECT * FROM myschema.prperties";
    private static String ADD_PROPERTIES = "INSERT INTO myschema.prperties(login,cardnumb) " +
            "VALUES (?,?)";
    private static String DELETE_ALLCARDS_BYLOGIN = "DELETE * FROM myschema.prperties " +
            "WHERE login = ?";
    private static String DELETE_ALL = "DELETE * FROM myschema.prperties";
    private static String DELETE_BY_CARDNUMB = "DELETE * FROM myschema.prperties " +
            "WHERE cardnumb = ?";

    public PropertiesDAO(Connection connectionA) {
        super(connectionA);
    }

    public void deleteAllByLogin(String login) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALLCARDS_BYLOGIN);
        preparedStatement.setString(1,login);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deleteAllByCardNumb(int curdNumb) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_CARDNUMB);
        preparedStatement.setInt(1,curdNumb);
        preparedStatement.execute();
        preparedStatement.close();
    }
    public void deleteALL(){}

    public void addProperties(String login,int cardnumb) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_PROPERTIES);
        preparedStatement.setString(1,login);
        preparedStatement.setInt(2,cardnumb);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<PropertiesLC> getAllProperties() throws SQLException {
        List<PropertiesLC> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new PropertiesLC(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getInt("cardnumb")
            ));
        }

        preparedStatement.close();
        resultSet.close();

        return list;
    }

    public List<Integer> getCurdNumbByLogin(String login) throws SQLException {
        List<Integer> list = new LinkedList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_CARDNUMB_BY_LOGIN);
        preparedStatement.setString(1,login);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(resultSet.getInt("cardnumb"));
        }

        preparedStatement.close();
        resultSet.close();

        return list;
    }

    public String getLoginByCardNumb(int cardnumb) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_LOGIN_BY_CARDNUMB);
        preparedStatement.setInt(1,cardnumb);
        ResultSet resultSet = preparedStatement.executeQuery();

        String login = "";
        while(resultSet.next()){
            login = resultSet.getString("login");
        }

        preparedStatement.close();
        resultSet.close();

        return login;
    }
}
