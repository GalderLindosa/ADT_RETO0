/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ire22
 */
public class ImplementacionFichero implements ShopDAOF{
    //SIngleton Instance
   private static ImplementacionFichero instance;
   
   
    public static ImplementacionFichero getInstance(){
        if(instance == null){
            ImplementacionFichero instance = new ImplementacionFichero();
        }
        return instance;
    }
    
    @Override
    public void fillDataOrder(File fichO) {
		ObjectOutputStream oos =null;
                LocalDate orderDate = LocalDate.of(2026, 9, 22);
                LocalDate endDate = LocalDate.of(2026, 9, 30);
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichO));

			//oos.writeObject(new Order(321,546,orderDate,endDate,true) );
			//oos.writeObject(new Order(123,675,orderDate,false));
	
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }    
    
    @Override
    public boolean existCustomer(File fichO, int id) {
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
    //hago un arraylist de pedidos pero me guardo solo los que tengan ese id
    @Override
    public ArrayList<Order> pedidosCliente(File fichO, int id) {
    boolean finArchivo = false;
    ArrayList<Order> aOrders = new ArrayList<>();

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichO))) {
        while (!finArchivo) {
            try {
                Order o = (Order) ois.readObject();

                if (o.getIdCostumer() == id) {
                    aOrders.add(o); 
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
    return aOrders;
}
    
    //View order history for a specific product(saltar el popUp)
    //preguntar cual quiere 
    //miro en todos los orders entro en sus arraylist y miro si esta ese producto (no se ha enocntrado o no se encuentra)
    //me lo guardo en un arrayList y lo enseño 
    
    @Override
    //me guardo en un arrayList de orders solo los que tengan ese producto 
    public ArrayList<Order> productos(File fichO, int id) {
    boolean finArchivo = false;
    ArrayList<Order> aOrders = new ArrayList<>();

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichO))) {
        while (!finArchivo) {
            try {
                Order o = (Order) ois.readObject();

                ArrayList<Integer> aProd = o.getaProducts();
              
                for (int i=0; i<aProd.size();i++) {
                        if (aProd.get(i) == id) {
                    aOrders.add(o); 
                        }
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
    return aOrders;
}
}
