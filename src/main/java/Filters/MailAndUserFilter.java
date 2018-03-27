package Filters;

import Controllers.RequestContext;
import DataBase.Connection.ConnectionGetter;
import DataBase.PropertiesDAO;
import Logic.AccessEnum;
import Logic.CardLogic;
import Resourses.ConfigurationManager;
import Resourses.MessageManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/validationFilter")
public class MailAndUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter("command").toUpperCase();
        switch(command){
            case "DELETE": {
                String login = (String) request.getSession().getAttribute("user");
                if(login.equals("admin")){
                    filterChain.doFilter(request,response);
                }
                break;
            }
            case "ORDERS": {
                String login = (String) request.getSession().getAttribute("user");
                if(login.equals("admin")){
                    filterChain.doFilter(request,response);
                }
                break;
            }
            case "PRODUCTREDIRECT":{
                String login = (String) request.getSession().getAttribute("user");
                if(login.equals("admin")){
                   filterChain.doFilter(request,response);
                }
                break;
            }
            case "REGISTER":{
                String mail = request.getParameter("mail");
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                if(isValidLogin(login) && isValidPassword(password)){
                    filterChain.doFilter(request,response);
                }else{
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                            ConfigurationManager.getProperty("path.page.register")
                    );
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.login"));
                    requestDispatcher.forward(request,response);
                }

                if(isValidEmailAddress(mail)) {
                    filterChain.doFilter(request, response);
                }else{
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                            ConfigurationManager.getProperty("path.page.register")
                    );
                    request.setAttribute("errorLoginPassMessage",
                            MessageManager.getProperty("message.mail"));
                    requestDispatcher.forward(request,response);
                }
                break;
            }
            case "ADD_CARD":{
                if(request.getSession().getAttribute("userType") == AccessEnum.USER){
                    if(request.getParameter("cardNumb").length() == 8){
                        if(!CardLogic.isExists(request.getParameter("cardNumb"))){
                            filterChain.doFilter(request, response);
                        }else {
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                                    ConfigurationManager.getProperty("path.page.cardReg"));
                            request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.cardexist"));
                            requestDispatcher.forward(request,response);
                        }
                    }
                    else{
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                                ConfigurationManager.getProperty("path.page.cardReg"));
                        request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.carderror"));
                        requestDispatcher.forward(request,response);
                    }
                }
                else{
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(
                            ConfigurationManager.getProperty("path.page.cardReg"));
                    request.setAttribute("errorLoginPassMessage",MessageManager.getProperty("message.carderror"));
                    requestDispatcher.forward(request,response);
                }
                break;
            }

            default:{
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|" +
                "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isValidLogin(String login){
        if(login.length()>6)return true;
        return false;
    }
    public boolean isValidPassword(String password){
        if(password.length() > 10 && (password.contains("#")|password.contains("%")|password.contains("&"))){
            return true;
        }
        return false;
    }
}
