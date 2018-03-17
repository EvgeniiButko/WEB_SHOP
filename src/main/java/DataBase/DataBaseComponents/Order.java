package DataBase.DataBaseComponents;

public class Order extends Bean {
    private String phone;
    private int productid;
    private String userName;

    public Order(int id, String phone, String userName, int productid) {
        super(id);
        this.phone = phone;
        this.productid = productid;
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }
}
