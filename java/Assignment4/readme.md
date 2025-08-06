# JDBC Store Inventory Management App

A simple Java console application for managing store inventory using JDBC and MySQL.

## Features

- Add, update, and delete products
- View products with pagination
- Record inventory IN/OUT transactions with proper transaction handling
- Prevents SQL injection using `PreparedStatement`
- Uses manual JDBC (no Spring or ORM)

## 📁 Project Structure

Assignment4/
├── DBUtil.java
├── Product.java
├── ProductDAO.java
└── Store.java

## Database Schema (MySQL)

Run this in MySQL Workbench:

```sql
CREATE DATABASE store_db;
USE store_db;

CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    quantity INT NOT NULL,
    transaction_type ENUM('IN', 'OUT') NOT NULL,
    transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

## Setup Instructions

1. **MySQL**: Make sure MySQL is running and the `store_db` database is created as shown above.
2. **Update Credentials**: In `DBUtil.java`, update the `USER` and `PASSWORD` fields with your MySQL credentials.
3. **Compile**: Use any Java IDE (e.g., IntelliJ) or terminal:
   ```bash
   javac DBUtil.java Product.java ProductDAO.java Store.java
   ```
4. **Run**:
   ```bash
   java Store
   ```

