package Logic;

import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AccessLogic {
    public static boolean isAccess(AccessEnum accessEnum, String page){
        if(accessEnum == AccessEnum.ADMIN)return true;
        if(accessEnum == AccessEnum.USER){
            if(ConfigurationManager.getProperty("path.page.ban").equals(page))return false;
        }
        return false;
    }
}
