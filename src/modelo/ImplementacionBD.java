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
}
