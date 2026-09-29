package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import utilidades.Utilidades;

public class ImplementacionBD implements ShopDAO {

    // Atributos
    private Connection con;
    private PreparedStatement stmt;
    private ResourceBundle configFile;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    private ResultSet rs;

    // Sentencias SQL
    final String SQL_CustomerId = "SELECT * FROM CUSTOMER WHERE ID = ?";
    final String SQL_InsertProduct = "INSERT INTO PRODUCT (ID, NAME_P, PRICE, CATEGORY, STOCK, ROOT) VALUES (?, ?, ?, ?, ?, ?)";
    final String SQL_InsertClient = "INSERT INTO CLIENT_S (ID, NAME_C, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";
    final String SQL_VerProductos = "SELECT * FROM PRODUCT WHERE STOCK > 0";
    final String SQL_OrdersByCustomer = "SELECT orderId, orderDate, total FROM ORDERS WHERE customerId = ?";
    final String SQL_UpdateStock = "UPDATE PRODUCT SET STOCK=? WHERE ID=?";
    final String SQL_ProductExists = "SELECT ID FROM PRODUCT WHERE ID=?";

    // Singleton
    private static ImplementacionBD instance;

    private ImplementacionBD() {
        this.configFile = ResourceBundle.getBundle("configClase");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }
    
    public static ImplementacionBD getInstance(){
        if(instance == null){
             instance = new ImplementacionBD();
        }
        return instance;
    }

    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, userBD, passwordBD);
        } catch (SQLException e) {
            System.out.println("Error al abrir la BD");
            e.printStackTrace();
        }
    }

    // -------------------- REGISTRAR PRODUCTO --------------------
    public boolean registrarProducto(Product p) {

        openConnection();
        boolean ok = false;

        try {
            stmt = con.prepareStatement(SQL_InsertProduct);

            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getCategory().toString());
            stmt.setInt(5, p.getStock());
            stmt.setString(6, p.getPath());

            ok = stmt.executeUpdate() > 0;

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error registrando producto: " + e.getMessage());
        }

        return ok;
    }

    // -------------------- REGISTRAR CLIENTE --------------------
    public boolean registrarCliente(Client c) {

        openConnection();
        boolean ok = false;

        try {
            stmt = con.prepareStatement(SQL_InsertClient);

            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhoneNumber());
            stmt.setString(5, c.getAddress());

            ok = stmt.executeUpdate() > 0;

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error registrando cliente: " + e.getMessage());
        }

        return ok;
    }

    // -------------------- VER PRODUCTOS --------------------
    public void verProductos() {

        List<Product> productos = new ArrayList<>();
        openConnection();

        try {
            stmt = con.prepareStatement(SQL_VerProductos);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {

                Product producto = new Product();

                producto.setId(resultado.getInt("ID"));
                producto.setName(resultado.getString("NAME_P"));
                producto.setPrice(resultado.getDouble("PRICE"));
                producto.setCategory(Category.valueOf(resultado.getString("CATEGORY")));
                producto.setStock(resultado.getInt("STOCK"));
                producto.setPath(resultado.getString("ROOT"));

                productos.add(producto);
                System.out.println(producto);
            }

            resultado.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error mostrando productos: " + e.getMessage());
        }
    }

    // -------------------- OBTENER CLIENTE POR ID --------------------
    @Override
    public Client getCustomerById(int id) {

        Client customer = null;
        openConnection();

        try {
            stmt = con.prepareStatement(SQL_CustomerId);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Client();
                customer.setId(rs.getInt("ID"));
                customer.setName(rs.getString("NAME"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setPhoneNumber(rs.getString("PHONE"));
                customer.setAddress(rs.getString("ADDRESS"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error obteniendo cliente: " + e.getMessage());
        }

        return customer;
    }

    // -------------------- EDITAR STOCK --------------------
    @Override
    public void editStock() {

        Product product = new Product();
        boolean exists;

        do {
            System.out.println("Enter the product's identifier");
            product.setId(Utilidades.leerInt());

            exists = productExists(product);

            if (!exists) {
                System.out.println("ERROR, THAT IDENTIFIER DOESN'T EXIST");
            }

        } while (!exists);

        do {
            System.out.println("Enter the product's new stock");
            product.setStock(Utilidades.leerInt());

            if (product.getStock() < 0) {
                System.out.println("STOCK CANNOT BE LESS THAN 0");
            }

        } while (product.getStock() < 0);

        openConnection();

        try {
            stmt = con.prepareStatement(SQL_UpdateStock);
            stmt.setInt(1, product.getStock());
            stmt.setInt(2, product.getId());

            if (stmt.executeUpdate() == 0) {
                System.out.println("IT WAS NOT POSSIBLE TO UPDATE THE STOCK.");
            } else {
                System.out.println("STOCK UPDATED SUCCESSFULLY");
            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error actualizando stock");
            e.printStackTrace();
        }
    }

    // -------------------- PRODUCT EXISTS --------------------
    @Override
    public boolean productExists(Product product) {

        boolean exists = false;
        openConnection();

        try {
            // Prepare the SQL query
            stmt = con.prepareStatement(SQL_ProductExists);
            stmt.setInt(1, product.getId());

            rs = stmt.executeQuery();

            exists = rs.next();

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error comprobando producto");
            e.printStackTrace();
        }

        return exists;
    }
}
