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
import java.util.Arrays;

/**
 *
 * @author ire22
 */
public class ImplementacionFichero implements ShopDAOF {

    // Singleton Instance
    private static ImplementacionFichero instance;

    private ImplementacionFichero() {
        // constructor vacío
    }

    public static ImplementacionFichero getInstance() {
        if (instance == null) {
            instance = new ImplementacionFichero();  // AHORA SÍ
        }
        return instance;
    }

    @Override
    public void fillDataOrder(File fichO) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fichO));

        oos.writeObject(new Order(101, 1,LocalDate.of(2026,9,22),LocalDate.of(2026,9,25),true,new ArrayList<Integer>(Arrays.asList(1,3))));
        oos.writeObject(new Order(102, 3,LocalDate.of(2026,9,23),false,new ArrayList<Integer>(Arrays.asList(2,5))));
        oos.writeObject(new Order(103, 5,LocalDate.of(2026,9,20),LocalDate.of(2026,9,28),true,new ArrayList<Integer>(Arrays.asList(4))));
        oos.writeObject(new Order(104, 2,LocalDate.of(2026,9,24),false,new ArrayList<Integer>(Arrays.asList(6,3))));
        oos.writeObject(new Order(105, 4,LocalDate.of(2026,9,21),LocalDate.of(2026,9,27),true,new ArrayList<Integer>(Arrays.asList(1,2,5))));
        oos.writeObject(new Order(106, 4,LocalDate.of(2026,9,18),LocalDate.of(2026,9,27),true,new ArrayList<Integer>(Arrays.asList(4,3,6))));
       
        
        oos.close();
        } catch (IOException e) {
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
                        finArchivo = true;
                    }
                } catch (EOFException e) {
                    finArchivo = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el fichero.");
        }

        return clienteExiste;
    }

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
                    finArchivo = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el fichero.");
        }

        return aOrders;
    }

    @Override
    public ArrayList<Order> productos(File fichO, int id) {
        boolean finArchivo = false;
        ArrayList<Order> aOrders = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichO))) {
            while (!finArchivo) {
                try {
                    Order o = (Order) ois.readObject();
                    ArrayList<Integer> aProd = o.getaProducts();

                    for (int prodId : aProd) {
                        if (prodId == id) {
                            aOrders.add(o);
                        }
                    }

                } catch (EOFException e) {
                    finArchivo = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error leyendo el fichero.");
        }

        return aOrders;
    }
}
