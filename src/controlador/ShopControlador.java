/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author Unai.Ibarguren
 */
public class ShopControlador {
    private ImplementacionBD dao;
    private ImplementacionFichero daoF;
    
    public ShopControlador(){
        this.dao = ImplementacionBD.getInstance();
    }
    
    public Client getCustomerById(int id){
        return dao.getCustomerById(id);
    }
    
    public void fillDataOrder(File fichO){
       daoF.fillDataOrder(fichO);
    }
    
    public ArrayList<Order> pedidosCliente(File fichO, int id){
      return  daoF.pedidosCliente(fichO,id);
    }
     public ArrayList<Order> productos(File fichO, int id) {
         return daoF.productos(fichO,id);
     }
}
