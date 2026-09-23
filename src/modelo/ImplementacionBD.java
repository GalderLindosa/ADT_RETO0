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
import utilidades.Utilidades;

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
    private ResultSet rs;

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

    //SQL STATEMENTS
    final String SQLUPDATE_STOCK = "UPDATE PRODUCT SET STOCK=? WHERE ID=?";
    final String SQLSHOW_PRODUCTS = "SELECT ID FROM PRODUCTO";

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
            
        } catch(SQLException e){
            System.out.println("AN ERROR HAS OCURRED WHILE TRYING TO UPDATE THE PRODUCT'S STOCK");
            e.printStackTrace();
        }

    }

    @Override
    public boolean productExists(Product product) {
        boolean exists = false;
        this.openConnection();

        try {
            // Prepare the SQL query
            stmt = con.prepareStatement(SQLSHOW_PRODUCTS);
            stmt.setInt(1, product.getId());
            //Executes the SQL query
            rs = stmt.executeQuery();
            // If there are results it means the user exists
            if (rs.next()) {
                exists = true;
            }
            // close everything
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
