package Commands;

import DataBase.Connection.ConnectionGetter;
import DataBase.ProductDAO;
import Logic.AccessEnum;
import Logic.LoginLogic;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;

import javax.security.auth.login.Configuration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "Login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if(LoginLogic.checkLogin(login,pass)){
            if(login.equals("admin")) {
                session.setAttribute("userType", AccessEnum.ADMIN);
            }else session.setAttribute("userType", AccessEnum.USER);
            session.setAttribute("user",login);

            //определение пути к main.jsp
            if(session.getAttribute("userType") == AccessEnum.ADMIN ||
                    session.getAttribute("userType") == AccessEnum.USER) {
                try {
                    request.setAttribute("products", new ProductDAO(ConnectionGetter.getConnection()).getAll());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                page = ConfigurationManager.getProperty("path.page.main");
            }
            if(session.getAttribute("userType") == AccessEnum.USER)
                page = ConfigurationManager.getProperty("path.page.user");

        }else{
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        return page;
    }
}
