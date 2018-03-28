package Commands;

import DataBase.ClientDAO;
import DataBase.Connection.ConnectionGetter;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;
import Resourses.TimeMemory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ConfirmationPasswordCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String confirm = request.getParameter("confirm");
        String timelogin = (String) request.getSession().getAttribute("timelogin");
        String password = null;
        try {
            password = new ClientDAO(ConnectionGetter.getConnection()).getClientPasswordByLogin(timelogin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (confirm.equals(TimeMemory.getInstance().get(timelogin))) {
            page = ConfigurationManager.getProperty("path.page.confirmation_page");
            request.setAttribute("wrongInput", "your password " + password);
        } else {
            page = ConfigurationManager.getProperty("path.page.confirmation_page");
            request.setAttribute("wrongInput", MessageManager.getProperty("message.wrongconfirmation"));
        }

        request.getSession().removeAttribute("timelogin");
        return page;
    }
}
