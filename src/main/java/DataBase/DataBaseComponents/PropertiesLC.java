package DataBase.DataBaseComponents;

public class PropertiesLC extends Bean {
    private String login;
    private int cardNumb;

    public PropertiesLC(int id, String login, int cardNumb) {
        super(id);
        this.login = login;
        this.cardNumb = cardNumb;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getCardNumb() {
        return cardNumb;
    }

    public void setCardNumb(int cardNumb) {
        this.cardNumb = cardNumb;
    }
}
