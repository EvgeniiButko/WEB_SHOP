package Commands;

import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ProductRedirecting implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.creatingProd");
    }
}
