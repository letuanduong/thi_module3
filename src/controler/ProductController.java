package controler;

import product.ProductDAO;
import service.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product")
public class ProductController extends HttpServlet {

    private DBConnection connection = DBConnection.getInstance();
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        doAction(action, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        double _price = Double.parseDouble(price);
        String quantity = req.getParameter("quantity");
        int _quantity = Integer.parseInt(quantity);
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        String danh_muc = req.getParameter("danh_muc");
        productDAO.insertProduct(name, _price, _quantity, color, description, danh_muc);
    }

    private void doAction(String action, HttpServletRequest req, HttpServletResponse resp) {

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "list":
                    getList(req, resp);
                    break;
                case "insert":
                    _insertProduct(req, resp);
                    break;
                case "update" :
                    String idParam = req.getParameter("price");
                    double _price = Double.parseDouble(idParam);
                    String _name1 = req.getParameter("name");
                    updateProduct(_price, _name1, req, resp);
                    break;
                case "delete":
                    String _name = req.getParameter("name");
                    deleteByName(_name, req, resp);
                    break;
                default:
                    PrintWriter writer = resp.getWriter();
                    writer.print("Cung cap action truoc khi thuc hien");
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteByName(String _name, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            productDAO.getByProductName(_name);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("delete-result.jsp");
            requestDispatcher.forward(req, resp);
            getList(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("loi khi xoa san pham co ten la " + _name);
        }
    }

    private void _insertProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("get_list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void getList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException  {
        req.setAttribute("products", productDAO.getListProduct());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("product-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void  updateProduct(double _price, String _name1, HttpServletRequest req, HttpServletResponse resp ) {
        try {
            productDAO.updateProduct(_price, _name1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("update-result.jsp");
            requestDispatcher.forward(req, resp);
            getList(req, resp);
        } catch (Exception e) {
        }
    }

}
