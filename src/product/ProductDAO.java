package product;

import service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends HttpServlet {

    private ProductDAO productDAO;
    private DBConnection dbconnection;

    public ProductDAO(DBConnection dbconnection)
    {
        this.dbconnection = dbconnection;
    }

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO(dbconnection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("command");
        doAction(action, req, resp);
    }

    private void doAction(String action, HttpServletRequest req, HttpServletResponse resp) {
        if(action == null){
            action = "";
        }
        switch (action){
            case "list" :

                break;
            case "getById" :

                break;
            case "deleteByName" :

                break;

            case

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }




    public List<Product> getCustomers() {

        List<Product> customers = new ArrayList<>();
        String sql = "SELECT id, name FROM khoa_hoc";

        try {
            Statement statement = this.connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString(2);

                Product customer = new Product(id, name);
                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi câu lệnh SQL");
        }

        return customers;
    }

    public void save(String customerName) {

        String sql = "INSERT INTO customers (name) VALUES (?)";

        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setString(1, customerName);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL Insert Into");
        }

    }

    public void deleteById(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thực thi lệnh SQL DELETE");
        }
    }
}
