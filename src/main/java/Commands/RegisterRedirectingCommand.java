package Commands;

import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RegisterRedirectingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        page = ConfigurationManager.getProperty("path.page.register");

        return page;
    }
}
