package DataBase.DataBaseComponents;

public class Product extends Bean{
    private String name;
    private String information;
    private String url;
    private int prize;

    public Product(int id, String name, String information, String url, int prize) {
        super(id);
        this.name = name;
        this.information = information;
        this.url = url;
        this.prize = prize;
    }

    Product(int id) {
        super(id);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
