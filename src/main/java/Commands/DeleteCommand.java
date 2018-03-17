package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.ProductDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        String id = request.getParameter("id");
        try {
            new ProductDAO(ConnectionGetter.getConnection()).deleteProduct(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
