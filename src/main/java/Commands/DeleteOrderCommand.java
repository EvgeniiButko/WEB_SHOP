package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.OrderDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String id = (String) request.getParameter("ID");

        try {
            OrderDAO orderDAO = new OrderDAO(ConnectionGetter.getConnection());
            orderDAO.deleteOrderById(Integer.parseInt(id));
            page = ConfigurationManager.getProperty("path.page.orders");
            request.setAttribute("orders",orderDAO.getAllOrders());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
