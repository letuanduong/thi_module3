package product;

import java.util.List;

public interface IProductDAO {
    public Product getByProductName(String _name);
    public void deleteByProductName(String _name);
    public void insertProduct(String name, double price, int quantity, String color, String description, String danh_muc);
    public List<Product> getListProduct();
    public void updateProduct(double _price, String _name);
}
