package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.ProductDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ProductAddingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        ProductDAO productDAO = null;
        try {
            productDAO = new ProductDAO(ConnectionGetter.getConnection());
            productDAO.addProduct(
                    request.getParameter("name"),
                    request.getParameter("information"),
                    request.getParameter("url"),
                    Integer.parseInt(request.getParameter("prize")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            request.setAttribute("products", new ProductDAO(ConnectionGetter.getConnection()).getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
