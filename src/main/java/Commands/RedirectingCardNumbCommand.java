package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.ProductDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RedirectingCardNumbCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String cardNumb = request.getParameter("cardNumb");
        request.setAttribute("cardNumb", cardNumb);
        try {
            request.setAttribute("products", new ProductDAO(ConnectionGetter.getConnection()).getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ConfigurationManager.getProperty("path.page.user");
    }
}
