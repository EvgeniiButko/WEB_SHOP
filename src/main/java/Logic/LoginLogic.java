package Logic;

import DataBase.ClientDAO;
import DataBase.Connection.ConnectionGetter;

import java.sql.SQLException;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass){
        try {
            String pass = new ClientDAO(ConnectionGetter.getConnection()).getClientPasswordByLogin(enterLogin);
            if(pass.length() > 1 && pass.equals(enterPass)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
