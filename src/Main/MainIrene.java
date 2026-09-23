/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import controlador.ShopControlador;
import java.io.EOFException;
import modelo.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import utilidades.Utilidades;

/**
 *
 * @author ire22
 */
public class MainIrene {
    
    private ShopControlador cont; 
    
        public static void fillDataOrder(File fichO) {
		ObjectOutputStream oos =null;
                LocalDate orderDate = LocalDate.of(2026, 9, 22);
                LocalDate endDate = LocalDate.of(2026, 9, 30);
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichO));

			/*oos.writeObject(new Order(321,546,orderDate,endDate,true) );
			oos.writeObject(new Order(123,675,orderDate,false));*/
	
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}    
        
        public static void main(String[] args){
          //  this.cont = controlador;
            
            int idCustomer;
            boolean existe; 
            Client client=null; 
            File fichO=new File("order.dat");
            
            if(!fichO.exists()) {
                fillDataOrder(fichO);
            }
            
            do{
                System.out.println("Enter the customer ID:");
                idCustomer=Utilidades.leerInt();
                existe=existCustomer(fichO,idCustomer);//metodo para mirar si existe ese cliente 
                if(existe){
                // client=cont.getCustomerById(idCustomer);
                        
                    
                }
               
                //conceto con la base de datos para mirar a los clientes
                //le eneseño los id de pedido que tiene y me lo guardo en una variable 
                //abro fichero y muestro el pedido con ese id 
            
                if(!existe){
                    System.out.println("ID not found, please enter it again:");
                }
            }while(!existe);
          
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
