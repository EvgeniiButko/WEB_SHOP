package DataBase;

import DataBase.DataBaseComponents.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDAO extends AbstractDAO{
    private String GET_USER_MONEY_BY_CARDNUMB = "SELECT money FROM myschema.usercard " +
            "WHERE cardNumb = ?";
    private String GET_ALL_CARDS = "SELECT * FROM myschema.usercard";
    private String DELETE_USERCARD_BY_CARDNUMB = "DELETE * from myschema.usercard " +
            "WHERE cardNumb = ?";
    private String ADD_NEW_CARD = "INSERT INTO myschema.usercard(cardNumb,money) " +
            "VALUES (?,?)";

    public CardDAO(Connection connectionA) {
        super(connectionA);
    }

    public double getUserMoneyByCardNumb(int cardnumb) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_USER_MONEY_BY_CARDNUMB);
        preparedStatement.setInt(1,cardnumb);

        ResultSet resultSet = preparedStatement.executeQuery();
        Double result = 0.0;
        while(resultSet.next()){
            result = resultSet.getDouble("money");
        }

        preparedStatement.close();
        resultSet.close();
        return result;
    }

    public List<Card> getAllCards() throws SQLException {
        List<Card> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                GET_ALL_CARDS);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            list.add(new Card(resultSet.getInt("id"),
                              resultSet.getInt("cardNumb"),
                              resultSet.getDouble("money")));
        }

        resultSet.close();
        preparedStatement.close();
        return list;
    }

    public void addNewCard(int cardnumb, double money) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                ADD_NEW_CARD);
        preparedStatement.setInt(1,cardnumb);
        preparedStatement.setDouble(2,money);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
