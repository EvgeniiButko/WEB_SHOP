package Commands.Factory;

import Commands.ActionCommand;
import Commands.CommandEnum;
import Commands.EmptyCommand;
import Controllers.RequestContext;
import Resourses.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(final RequestContext requestContext){
        ActionCommand current = new EmptyCommand();

        String[] action = requestContext.getRequestParameters().get("command");
        if(action == null || action.length != 1){
            return current;
        }


        String command = action[0].toUpperCase();
        try {
            ActionCommand actionCommand = CommandEnum.valueOf(command).getCurrentCommand();
            current = actionCommand;
        } catch (IllegalArgumentException e) {
            return null;
        }

        return current;
    }
}
