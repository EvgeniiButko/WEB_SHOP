package DataBase.DataBaseComponents;

import java.io.Serializable;

public class Bean implements Serializable{
    private int id;

    Bean(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
