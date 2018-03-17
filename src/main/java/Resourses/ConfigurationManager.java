package Resourses;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private final static ResourceBundle resourceBandle = ResourceBundle.getBundle("config");

    private ConfigurationManager(){}
    public static String getProperty(String key){
        return resourceBandle.getString(key);
    }
}
