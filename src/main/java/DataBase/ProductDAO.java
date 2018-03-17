package DataBase;

import DataBase.DataBaseComponents.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO{
    private String GET_ALL_PRODUCTS = "SELECT * FROM myschema.products";
    private String ADD_PRODUCT = "INSERT INTO myschema.products(name,information,url,prize) " +
            "VALUES (?,?,?,?)";
    private String DELETE_PRODUCT_BYID = "DELETE * FROM myschema.products WHERE id = ?";

    public ProductDAO(Connection connectionA) {
        super(connectionA);
    }
    public void deleteProduct(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BYID);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void addProduct(String name,String information,String url,int prize) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,information);
        preparedStatement.setString(3,url);
        preparedStatement.setInt(4,prize);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<Product> getAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PRODUCTS);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            list.add(new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("information"),
                    resultSet.getString("url"),
                    resultSet.getInt("prize")
            ));
        }

        preparedStatement.close();
        resultSet.close();

        return list;
    }
}
