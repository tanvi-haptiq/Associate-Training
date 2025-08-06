import java.sql.SQLException;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Insert Product\n2. Update Product\n3. Delete Product\n4. View Products\n5. Transaction\n6. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        Product p = new Product();
                        System.out.print("Name: ");
                        p.name = sc.next();
                        System.out.print("Price: ");
                        p.price = sc.nextDouble();
                        System.out.print("Qty: ");
                        p.quantity = sc.nextInt();
                        System.out.print("Category ID: ");
                        p.categoryId = sc.nextInt();
                        dao.insertProduct(p);
                        break;
                    case 2:
                        Product pu = new Product();
                        System.out.print("Product ID: ");
                        pu.id = sc.nextInt();
                        System.out.print("New Name: ");
                        pu.name = sc.next();
                        System.out.print("New Price: ");
                        pu.price = sc.nextDouble();
                        System.out.print("New Qty: ");
                        pu.quantity = sc.nextInt();
                        System.out.print("New Category ID: ");
                        pu.categoryId = sc.nextInt();
                        dao.updateProduct(pu);
                        break;
                    case 3:
                        System.out.print("Product ID to delete: ");
                        int delId = sc.nextInt();
                        dao.deleteProduct(delId);
                        break;
                    case 4:
                        System.out.print("Page No: ");
                        int page = sc.nextInt();
                        dao.listProducts(5, page); // 5 items per page
                        break;
                    case 5:
                        System.out.print("Product ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Qty: ");
                        int qty = sc.nextInt();
                        System.out.print("Type (IN/OUT): ");
                        String type = sc.next();
                        dao.performTransaction(pid, qty, type);
                        break;
                    case 6:
                        System.exit(0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}