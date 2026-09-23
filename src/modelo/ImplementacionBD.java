/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 *
 * @author Unai.Ibarguren
 */
public class ImplementacionBD implements ShopDAO {
    // Atributos
    private Connection con;
    private PreparedStatement stmt; //ejecutar sentencias sql
    // Los siguientes atributos se utilizan para recoger los valores del fich de
    // configuración
    private ResourceBundle configFile;
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;
    // Sentencias SQL
    final String SQL_CustomerId = "SELECT * FROM Customer WHERE id = ?";	
    final String SQL_OrdersByCustomer="SELECT orderId, orderDate, total FROM Orders WHERE customerId = ?";
    //SIngleton Instance
   private static ImplementacionBD instance;
    

// Para la conexion utilizamos un fichero de configuaraci n, config que
    // guardamos en el paquete control: (las pasa a una variable de l programa)
    private ImplementacionBD() {
            this.configFile = ResourceBundle.getBundle("configClase");
            //this.driverBD = this.configFile.getString("Driver");
            this.urlBD = this.configFile.getString("Conn");
            this.userBD = this.configFile.getString("DBUser");
            this.passwordBD = this.configFile.getString("DBPass");
    }
    
    public static ImplementacionBD getInstance(){
        if(instance == null){
            ImplementacionBD instance = new ImplementacionBD();
        }
        return instance;
    }
    
    //COPIAR--------------
    private void openConnection() {//abre la conexion con la base de datos
            try {
                    con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
            } catch (SQLException e) {
                    System.out.println("Error al intentar abrir la BD");
                    e.printStackTrace();
            } catch (Exception e) {
                    e.printStackTrace();
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

            // Si tienes una tabla de pedidos, aquí deberías cargarlos
            //customer.setOrders(loadOrdersForCustomer(id));
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
            /*Order o = new Order(
                rs.getInt("id"),
                rs.getInt("idCostumer"),
                rs.getDate("orderDate").toLocalDate(),
                //rs.getDate("endDate").toLocalDate(),
                rs.getBoolean("delivered")
            );*/

            //orders.add(o);
        }

        rs.close();
        stmt.close();

    } catch (SQLException e) {
        System.out.println("Error al cargar pedidos: " + e.getMessage());
    }

    return orders;
}

    
}
