/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.*;
/**
 *
 * @author Unai.Ibarguren
 */
public class ShopControlador {
    ShopDAO dao = new ImplementacionBD();
    
    public void editStock(){
         dao.editStock();
    }
    
    public boolean productExists(Product product){
        return dao.productExists(product);
    }
}
