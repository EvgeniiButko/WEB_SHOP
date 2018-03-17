package Commands;

import DataBase.ClientDAO;
import DataBase.Connection.ConnectionGetter;
import DataBase.DataBaseComponents.Client;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        ClientDAO clientDAO = null;
        String checkPassword = "";

        try {
            clientDAO = new ClientDAO(ConnectionGetter.getConnection());
            if(clientDAO != null)checkPassword = clientDAO.getClientPasswordByLogin(login);

        if(checkPassword.length() > 1){
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.regerror"));
            page = ConfigurationManager.getProperty("path.page.register");
        }else{
            request.setAttribute("user", login);

            //определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.user");
            clientDAO.addClient(login,password,mail);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
