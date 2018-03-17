package DataBase.DataBaseComponents;

public class Client extends Bean{
    private String login;
    private String password;
    private int solt;
    private String mail;

    public Client(final int id,
                  final String login,
                  final String password,
                  final String mail){
        super(id);
        this.login = login;
        this.mail = mail;
        this.password = password;
    }

    public int getSolt() {
        return solt;
    }

    public void setSolt(int solt) {
        this.solt = solt;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
