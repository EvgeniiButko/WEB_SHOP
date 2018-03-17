package Commands;

import DataBase.CardDAO;
import DataBase.Connection.ConnectionGetter;
import DataBase.PropertiesDAO;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AddUserCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        try{
            int cardNumb = Integer.parseInt(request.getParameter("cardNumb"));
            CardDAO cardDAO = new CardDAO(ConnectionGetter.getConnection());
            PropertiesDAO propertiesDAO = new PropertiesDAO(ConnectionGetter.getConnection());

            if(!propertiesDAO.getLoginByCardNumb(cardNumb).equals((String) request.getSession().getAttribute("user"))) {
                cardDAO.addNewCard(cardNumb,1500.0);
                propertiesDAO.addProperties((String) request.getSession().getAttribute("user"),cardNumb);

                page = ConfigurationManager.getProperty("path.page.user");
                request.setAttribute("succesfuly", MessageManager.getProperty("message.succes"));
            }else{
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.cardexist"));
                page = ConfigurationManager.getProperty("path.page.cardReg");
            }
        }catch (NumberFormatException e){
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.carderror"));
            page = ConfigurationManager.getProperty("path.page.cardReg");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return page;
    }
}
