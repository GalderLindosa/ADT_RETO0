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
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    private ResultSet rs;

    // Sentencias SQL
    final String CustomerId = "SELECT * FROM Customer WHERE id = ?";
    final String InsertarProducto = "INSERT INTO PRODUCT (ID, NAME_P, PRICE, CATEGORY, STOCK, ROOT) VALUES (?, ?, ?, ?, ?, ?)";
    final String InsertarCliente = "INSERT INTO CLIENT_S (ID, NAME_C, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";

    final String VER_PRODUCTOS = "SELECT * FROM PRODUCT WHERE STOCK > 0";
    final String SQL_CustomerId = "SELECT * FROM Customer WHERE id = ?";
    final String SQL_OrdersByCustomer = "SELECT orderId, orderDate, total FROM Orders WHERE customerId = ?";

    final String SQLUPDATE_STOCK = "UPDATE PRODUCT SET STOCK=? WHERE ID=?";
    final String SQLSHOW_PRODUCTS = "SELECT ID FROM PRODUCT WHERE ID=?";

    // Singleton
    private static ImplementacionBD instance;

    private ImplementacionBD() {
        this.configFile = ResourceBundle.getBundle("configClase");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    public static ImplementacionBD getInstance() {
        if (instance == null) {
            instance = new ImplementacionBD();
        }
        return instance;
    }

    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean registrarProducto(Product p) {

        openConnection();
        boolean ok = false;

        try {
            stmt = con.prepareStatement(InsertarProducto);

            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getName());
            stmt.setDouble(3, p.getPrice());
            stmt.setString(4, p.getCategory().toString());
            stmt.setInt(5, p.getStock());
            stmt.setString(6, p.getPath());

            if (stmt.executeUpdate() > 0) {
                ok = true;
            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error registering a product: " + e.getMessage());
        }

        return ok;
    }

    public boolean registrarCliente(Client c) {

        openConnection();
        boolean ok = false;

        try {

            stmt = con.prepareStatement(InsertarCliente);

            stmt.setInt(1, c.getId());
            stmt.setString(2, c.getName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getPhoneNumber());
            stmt.setString(5, c.getAddress());

            if (stmt.executeUpdate() > 0) {
                ok = true;
            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error registering a client: " + e.getMessage());
        }

        return ok;
    }

    public void verProductos() {

        List<Product> productos = new ArrayList<>();

        this.openConnection();

        try {

            stmt = con.prepareStatement(VER_PRODUCTOS);
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
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
    }

    @Override
    public Client getCustomerById(int id) {

        Client customer = null;

        this.openConnection();

        try {

            stmt = con.prepareStatement(SQL_CustomerId);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                customer = new Client();

                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setAddress(rs.getString("address"));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del cliente: " + e.getMessage());
        }

        return customer;
    }

    private ArrayList<Order> loadOrdersForCustomer(int id) {

        ArrayList<Order> orders = new ArrayList<>();

        try {

            stmt = con.prepareStatement(SQL_OrdersByCustomer);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                /*
                Order o = new Order(
                        rs.getInt("id"),
                        rs.getInt("idCostumer"),
                        rs.getDate("orderDate").toLocalDate(),
                        rs.getBoolean("delivered")
                );

                orders.add(o);
                */
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println("Error al cargar pedidos: " + e.getMessage());
        }

        return orders;
    }

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

        this.openConnection();

        try {

            stmt = con.prepareStatement(SQLUPDATE_STOCK);
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

            System.out.println("AN ERROR HAS OCURRED WHILE TRYING TO UPDATE THE PRODUCT'S STOCK");
            e.printStackTrace();
        }
    }

    @Override
    public boolean productExists(Product product) {

        boolean exists = false;

        this.openConnection();

        try {

            stmt = con.prepareStatement(SQLSHOW_PRODUCTS);
            stmt.setInt(1, product.getId());

            rs = stmt.executeQuery();

            if (rs.next()) {
                exists = true;
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("THE ID OF THE PRODUCT COULD NOT BE FOUND OR IDENTIFIED");
            e.printStackTrace();
        }

        return exists;
    }
}