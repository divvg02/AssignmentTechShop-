package main;

import dao.CustomerDAO;
import dao.ProductDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.InventoryDAO;
import entity.model.Customer;
import entity.model.Product;
import entity.model.Order;
import entity.model.OrderDetail;
import entity.model.Inventory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal; // Make sure to import BigDecimal
import java.time.LocalDate;


public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/techshopdb"; // Update with your database name
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = "password"; // Update with your database password

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // DAOs
            CustomerDAO customerDAO = new CustomerDAO(connection);
            ProductDAO productDAO = new ProductDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO(connection);
            InventoryDAO inventoryDAO = new InventoryDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("-----------------------------------------------------------------------------------------------------------------");

                System.out.println("********************************Welcome to TECH SHOP an electronic gadgets shop**********************************");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");

                System.out.println("***MENU:***");
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                System.out.println("1. Add Customer");
                System.out.println("2. Update Customer");
                System.out.println("3. Get Customer");
                System.out.println("4. Get All Customers");
                System.out.println("5. Delete Customer");
                System.out.println("6. Add Product");
                System.out.println("7. Update Product");
                System.out.println("8. Get Product");
                System.out.println("9. Get All Products");
                System.out.println("10. Delete Product");
                System.out.println("11. Place Order");
                System.out.println("12. Get Order");
                System.out.println("13. Delete Order");
                System.out.println("14. Add Inventory");
                System.out.println("15. Update Inventory");
                System.out.println("16. Get Inventory");
                System.out.println("17. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add Customer
                        System.out.print("Enter Customer ID: ");
                        int customerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine();
                        customerDAO.addCustomer(new Customer(customerId, firstName, lastName, email, phone));
                        System.out.println("Customer added successfully.");
                        break;

                    case 2:
                        // Update Customer
                        System.out.print("Enter Customer ID to update: ");
                        int updateCustomerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New First Name: ");
                        String newFirstName = scanner.nextLine();
                        System.out.print("Enter New Last Name: ");
                        String newLastName = scanner.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = scanner.nextLine();
                        System.out.print("Enter New Phone: ");
                        String newPhone = scanner.nextLine();
                        customerDAO.updateCustomer(new Customer(updateCustomerId, newFirstName, newLastName, newEmail, newPhone));
                        System.out.println("Customer updated successfully.");
                        break;

                    case 3:
                        // Get Customer
                        System.out.print("Enter Customer ID to retrieve: ");
                        int getCustomerId = scanner.nextInt();
                        Customer customer = customerDAO.getCustomer(getCustomerId);
                        if (customer != null) {
                            System.out.println("Customer Details: " + customer);
                        } else {
                            System.out.println("Customer not found.");
                        }
                        break;

                    case 4:
                        // Get All Customers
                        List<Customer> customers = customerDAO.getAllCustomers();
                        System.out.println("All Customers:");
                        for (Customer cust : customers) {
                            System.out.println(cust);
                        }
                        break;

                    case 5:
                        // Delete Customer
                        System.out.print("Enter Customer ID to delete: ");
                        int deleteCustomerId = scanner.nextInt();
                        customerDAO.deleteCustomer(deleteCustomerId);
                        System.out.println("Customer deleted successfully.");
                        break;

                    case 6:
                        // Add Product
                        System.out.print("Enter Product ID: ");
                        int productId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Product Name: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        // Change to BigDecimal
                        BigDecimal price = scanner.nextBigDecimal();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Product Description: "); // Optional: If you want to include a description
                        String description = scanner.nextLine(); // Get description input

                        // Create new Product object with description
                        productDAO.addProduct(new Product(productId, productName, price, description));
                        System.out.println("Product added successfully.");
                        break;

                    case 7:
                        // Update Product
                        System.out.print("Enter Product ID to update: ");
                        int updateProductId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Product Name: ");
                        String newProductName = scanner.nextLine();
                        System.out.print("Enter New Price: ");
                        // Change to BigDecimal
                        BigDecimal newPrice = scanner.nextBigDecimal();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Product Description: "); // Optional: If you want to include a description
                        String newDescription = scanner.nextLine(); // Get new description input

                        // Create new Product object with description
                        productDAO.updateProduct(new Product(updateProductId, newProductName, newPrice, newDescription));
                        System.out.println("Product updated successfully.");
                        break;


                    case 8:
                        // Get Product
                        System.out.print("Enter Product ID to retrieve: ");
                        int getProductId = scanner.nextInt();
                        Product product = productDAO.getProduct(getProductId);
                        if (product != null) {
                            System.out.println("Product Details: " + product);
                        } else {
                            System.out.println("Product not found.");
                        }
                        break;

                    case 9:
                        // Get All Products
                        List<Product> products = productDAO.getAllProducts();
                        System.out.println("All Products:");
                        for (Product prod : products) {
                            System.out.println(prod);
                        }
                        break;

                    case 10:
                        // Delete Product
                        System.out.print("Enter Product ID to delete: ");
                        int deleteProductId = scanner.nextInt();
                        productDAO.deleteProduct(deleteProductId);
                        System.out.println("Product deleted successfully.");
                        break;

                    case 11:
                        // Place Order
                        System.out.print("Enter Order ID: ");
                        int orderId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.print("Enter Customer ID: ");
                        int orderCustomerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        // Get order date from user
                        System.out.print("Enter Order Date (YYYY-MM-DD): ");
                        String orderDateInput = scanner.nextLine();
                        LocalDate orderDate = LocalDate.parse(orderDateInput); // Parse the input to LocalDate

                        // Get total amount from user
                        System.out.print("Enter Total Amount: ");
                        BigDecimal totalAmount = scanner.nextBigDecimal();
                        scanner.nextLine(); // Consume newline

                        // Create the Order object and place the order
                        orderDAO.addOrder(new Order(orderId, orderCustomerId, orderDate, totalAmount));
                        System.out.println("Order placed successfully.");
                        break;



                    case 12:
                        // Get Order
                        System.out.print("Enter Order ID to retrieve: ");
                        int getOrderId = scanner.nextInt();
                        Order order = orderDAO.getOrder(getOrderId);
                        if (order != null) {
                            System.out.println("Order Details: " + order);
                        } else {
                            System.out.println("Order not found.");
                        }
                        break;

                    case 13:
                        // Delete Order
                        System.out.print("Enter Order ID to delete: ");
                        int deleteOrderId = scanner.nextInt();
                        orderDAO.deleteOrder(deleteOrderId);
                        System.out.println("Order deleted successfully.");
                        break;

                    case 14:
                        // Add Inventory
                        System.out.print("Enter Inventory ID: ");
                        int inventoryId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Product ID: ");
                        int inventoryProductId = scanner.nextInt();
                        System.out.print("Enter Quantity Available: ");
                        int quantityAvailable = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        inventoryDAO.addInventory(new Inventory(inventoryId, inventoryProductId, quantityAvailable));
                        System.out.println("Inventory added successfully.");
                        break;

                    case 15:
                        // Update Inventory
                        System.out.print("Enter Inventory ID to update: ");
                        int updateInventoryId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter Product ID for this Inventory: ");
                        int newProductId = scanner.nextInt(); // Changed variable name to newProductId
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Quantity Available: ");
                        int newQuantityAvailable = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        inventoryDAO.updateInventory(new Inventory(updateInventoryId, newProductId, newQuantityAvailable)); // Use newProductId
                        System.out.println("Inventory updated successfully.");
                        break;



                    case 16:
                        // Get Inventory
                        System.out.print("Enter Inventory ID to retrieve: ");
                        int getInventoryId = scanner.nextInt();
                        Inventory inventory = inventoryDAO.getInventory(getInventoryId);
                        if (inventory != null) {
                            System.out.println("Inventory Details: " + inventory);
                        } else {
                            System.out.println("Inventory not found.");
                        }
                        break;

                    case 17:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            } while (choice != 17);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
