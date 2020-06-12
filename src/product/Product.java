package product;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private String color;
    private String description;
    private String danh_muc;

    public Product(String name, double price, int quantity, String color, String description, String danh_muc){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.danh_muc = danh_muc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDanh_muc(String danh_muc) {
        this.danh_muc = danh_muc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }

    public String getDanh_muc() {
        return danh_muc;
    }

    public String getDescription() {
        return description;
    }
}
