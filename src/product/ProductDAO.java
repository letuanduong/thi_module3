package product;

import service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    DBConnection connection;
    public ProductDAO(DBConnection connection) {
        this.connection = connection;
    }

    @Override
    public Product getByProductName(String _name) {
        Product product = null;
        String sql = "select  *from product where name = ?;";
        try {
            PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, _name );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getByte("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String danh_muc = resultSet.getString("danh_muc");
                product = new Product(_name, price, quantity, color, description, danh_muc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void deleteByProductName(String _name) {
        String sql = "delete from product where name = ?; ";
        try {
            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, "_name");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertProduct(String name, double price, int quantity, String color, String description, String danh_muc) {
        String sql = " insert into product(name, price, quantity, color, description, danh_muc) VALUE (?, ?, ?, ?, ?, ?); ";
        try {
            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setString(1, "name" );
            statement.setDouble(2, price);
            statement.setInt(3, quantity);
            statement.setString(4, "color");
            statement.setString(5, " description");
            statement.setString(6, "danh_muc");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getListProduct() {
        List<Product> products = new ArrayList();

        String sql = "select *from product;";
        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getByte("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                String danh_muc = resultSet.getString("danh_muc");
                Product product = new Product(name, price, quantity, color, description, danh_muc);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProduct(double _price, String _name) {
        String sql = "update product set price = ? where name = ?;";
        try {
            PreparedStatement statement = this.connection.getConnection().prepareStatement(sql);
            statement.setDouble(1, _price);
            statement.setString(2, _name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
