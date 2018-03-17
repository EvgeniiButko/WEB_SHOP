package Commands;

import DataBase.CardDAO;
import DataBase.Connection.ConnectionGetter;
import DataBase.DataBaseComponents.Product;
import DataBase.ProductDAO;
import DataBase.PropertiesDAO;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserBuyProduct implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id = request.getParameter("id");
        String user = (String) request.getSession().getAttribute("user");
        String cardNumber = (String) request.getParameter("cardNumb");

        Product product = null;
        List<Integer> cardNumb = null;
        CardDAO cardDAO  = null;
        try {
            request.setAttribute("products", new ProductDAO(ConnectionGetter.getConnection()).getAll());

            product = new ProductDAO(ConnectionGetter.getConnection()).getProductById(id);
            cardNumb = new PropertiesDAO(ConnectionGetter.getConnection()).getCurdNumbByLogin(user);
            cardDAO = new CardDAO(ConnectionGetter.getConnection());
            if(cardNumb != null) {
                double money = cardDAO.getUserMoneyByCardNumb(Integer.parseInt(cardNumber));
                double newMoney = money - product.getPrize();
                if(newMoney < 0) {
                    request.setAttribute("succesfuly", MessageManager.getProperty("message.luckOfMoney"));
                } else {
                    cardDAO.updateMoneyValue(Integer.parseInt(cardNumber), newMoney);
                }
            } else {
                request.setAttribute("succesfuly", MessageManager.getProperty("message.cardError"));
                page = ConfigurationManager.getProperty("path.page.user");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}
