package Commands;

import DataBase.ClientDAO;
import DataBase.Connection.ConnectionGetter;
import Logic.MailLogic;
import Resourses.ConfigurationManager;
import Resourses.TimeMemory;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;

/**
 * Command for confirming person
 */
public class ForgotPasswordCommand implements ActionCommand {
    /**
     * @param request
     * @return String page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String TIMELOGIN = (String) request.getParameter("timelogin");
        int confirmationNumber = new Random().nextInt(9000) + 1000;
        try {
            ClientDAO clientDAO = new ClientDAO(ConnectionGetter.getConnection());
            String userGmail = clientDAO.getClientMailByLogin(TIMELOGIN);
            TimeMemory.setInstance(TIMELOGIN, "" + confirmationNumber);

            if (userGmail != null) {
                MailLogic.sendConfirmationMessage(userGmail, 1,
                        "butkoevgenii@gmail.com", "" + confirmationNumber);
            }
            page = ConfigurationManager.getProperty("path.page.confirmation_page");
        } catch (SQLException | FileNotFoundException | MessagingException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("timelogin",TIMELOGIN);
        return page;
    }
}
