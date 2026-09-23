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
import modelo.*;
/**
 *
 * @author Unai.Ibarguren
 */
public class ShopControlador {
    private ImplementacionBD dao;
    
    public void verProductos(){
        dao.verProductos();
    }
    public ShopControlador(){
        this.dao = ImplementacionBD.getInstance();
    }
    
    public Client getCustomerById(int id){
        return dao.getCustomerById(id);
    }
  
    public static boolean existCustomer(File fichO, int id) {
      boolean clienteExiste = false;
      boolean finArchivo = false;

      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichO))) {
          while (!finArchivo) {
              try {
                  Order o = (Order) ois.readObject();

                  if (o.getIdCostumer() == id) {
                      clienteExiste = true;
                      finArchivo = true; // ya lo encontré
                  }
              } catch (EOFException e) {
                  finArchivo = true; // fin del fichero
              }
          }
      } catch (FileNotFoundException e) {
          System.out.println("No se encontró el fichero.");
      } catch (ClassNotFoundException e) {
          System.out.println("La clase Objeto no es válida.");
      } catch (IOException e) {
          System.out.println("Error leyendo el fichero.");
      }

      return clienteExiste;
    }
}
