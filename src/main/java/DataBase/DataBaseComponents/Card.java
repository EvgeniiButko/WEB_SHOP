package DataBase.DataBaseComponents;

public class Card extends Bean {
    private int сardNumb;
    private double money;

    public Card(int id, int cardNumb, double money) {
        super(id);
        сardNumb = cardNumb;
        this.money = money;
    }

    public int getCardNumb() {
        return сardNumb;
    }

    public double getMoney() {
        return money;
    }

    public void setCardNumb(int cardNumb) {
        сardNumb = cardNumb;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
