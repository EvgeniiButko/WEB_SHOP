package Commands;

import DataBase.DataBaseComponents.Card;
import Resourses.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChoosingCardCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Card> cardList = ShowingUserCardsCommand.getCardList(request);

        request.setAttribute("cards",cardList);
        page = ConfigurationManager.getProperty("path.page.choosing");

        return page;
    }
}
