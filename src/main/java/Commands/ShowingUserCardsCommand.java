package Commands;

import DataBase.CardDAO;
import DataBase.Connection.ConnectionGetter;
import DataBase.DataBaseComponents.Card;
import DataBase.DataBaseComponents.PropertiesLC;
import DataBase.PropertiesDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowingUserCardsCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        List<Integer> list = null;
        CardDAO cardDAO = null;
        try {
            list = new PropertiesDAO(ConnectionGetter.getConnection()).getCurdNumbByLogin(
                    (String) request.getSession().getAttribute("user"));
            cardDAO = new CardDAO(ConnectionGetter.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Card> cardList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            try {
                double money = cardDAO.getUserMoneyByCardNumb(list.get(i));
                cardList.add(new Card(0,list.get(i),money));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("cards",cardList);
        page = ConfigurationManager.getProperty("path.page.cards");

        return page;
    }
}
