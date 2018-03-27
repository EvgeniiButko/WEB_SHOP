package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.DataBaseComponents.Order;
import DataBase.OrderDAO;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        List<Order> orders = new ArrayList<>();
        try {
            orders = new OrderDAO(ConnectionGetter.getConnection()).getAllOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("orders",orders);

        return ConfigurationManager.getProperty("path.page.orders");
    }
}
