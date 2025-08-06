public class Product {
    public int id;
    public String name;
    public double price;
    public int quantity;
    public int categoryId;

    public Product(int id, String name, double price, int quantity, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Product() {} // No-arg constructor
}