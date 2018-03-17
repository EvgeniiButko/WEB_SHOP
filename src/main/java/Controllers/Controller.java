package Controllers;

import Commands.ActionCommand;
import Commands.Factory.ActionFactory;
import Logic.AccessLogic;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException {
        String page = null;

        RequestContext requestContext = new RequestContext(request,
                this.getServletConfig());
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(requestContext);

        page = command.execute(request);
        if(page != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request,response);
        }else{
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath()+page);
        }
    }
}
