package Logic;

import DataBase.CardDAO;
import DataBase.Connection.ConnectionGetter;

import java.sql.SQLException;

public class CardLogic {

    private CardLogic(){}

    public static boolean isExists(String numb){
        int number = 0;
        CardDAO cardDAO = null;

        try{
            number = Integer.parseInt(numb);
            cardDAO = new CardDAO(ConnectionGetter.getConnection());
        }catch (NumberFormatException e){
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(cardDAO != null && number != 0){
            try {
                double money = -1.0;
                money = cardDAO.getUserMoneyByCardNumb(number);
                if(money != -1.0)return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
