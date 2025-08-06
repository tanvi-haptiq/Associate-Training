import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDAO {
    public void insertProduct(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, price, quantity, category_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.name);
            ps.setDouble(2, p.price);
            ps.setInt(3, p.quantity);
            ps.setInt(4, p.categoryId);
            ps.executeUpdate();
            System.out.println("Product inserted.");
        }
    }

    public void updateProduct(Product p) throws SQLException {
        String sql = "UPDATE products SET name=?, price=?, quantity=?, category_id=? WHERE id=?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.name);
            ps.setDouble(2, p.price);
            ps.setInt(3, p.quantity);
            ps.setInt(4, p.categoryId);
            ps.setInt(5, p.id);
            ps.executeUpdate();
            System.out.println("Product updated.");
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Product deleted.");
        }
    }

    public void listProducts(int pageSize, int pageNo) throws SQLException {
        int offset = (pageNo - 1) * pageSize;
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";
        try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Price: %.2f | Qty: %d\n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getInt("quantity"));
            }
        }
    }

    public void performTransaction(int productId, int qty, String type) throws SQLException {
        String updateSql = type.equals("OUT") ?
                "UPDATE products SET quantity = quantity - ? WHERE id = ?" :
                "UPDATE products SET quantity = quantity + ? WHERE id = ?";
        String insertTransactionSql = "INSERT INTO transactions (product_id, quantity, transaction_type) VALUES (?, ?, ?)";

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement psUpdate = con.prepareStatement(updateSql);
                 PreparedStatement psTrans = con.prepareStatement(insertTransactionSql)) {

                psUpdate.setInt(1, qty);
                psUpdate.setInt(2, productId);
                psUpdate.executeUpdate();

                psTrans.setInt(1, productId);
                psTrans.setInt(2, qty);
                psTrans.setString(3, type);
                psTrans.executeUpdate();

                con.commit();
                System.out.println("Transaction committed.");
            }
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
                System.out.println("Transaction rolled back.");
            }
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }
}