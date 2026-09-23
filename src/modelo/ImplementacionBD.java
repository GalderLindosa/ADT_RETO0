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
    final String CustomerId = "SELECT * FROM Customer WHERE id = ?";
    final String InsertarProducto = "INSERT INTO PRODUCT (ID, NAME_P, PRICE, CATEGORY, STOCK, ROOT) VALUES (?, ?, ?, ?, ?, ?)";
    final String InsertarCliente = "INSERT INTO CLIENT_S (ID, NAME_C, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)";

    // Para la conexi n utilizamos un fichero de configuaraci n, config que
    // guardamos en el paquete control: (las pasa a una variable de l programa)
    public ImplementacionBD() {
        this.configFile = ResourceBundle.getBundle("configClase");
        //this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
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
//    public String Customer(int id) { 
//		// Abrimos la conexion
//		int id=0;
//                Client customer=null;
//		this.openConnection();
//		try {
//			stmt = con.prepareStatement(SQL);
//			stmt.setString(1, id);
//			ResultSet resultado = stmt.executeQuery();
//			//Si hay un resultado, el usuario existe
//			if (resultado.next()) {
//				Client c =new Client()resultado.getString("creatureName");
//			}
//			resultado.close();
//			stmt.close();
//			con.close();
//		} catch (SQLException e) {
//			System.out.println("Error al verificar credenciales: " + e.getMessage());
//		}
//		return nom;
//	}

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

}
