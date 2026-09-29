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
import utilidades.MyObjectOutputStream;

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

            Product p1 = new Product(1, "Nike Air Max", 129.99,Category.FOOTWEAR , 15, "Warehouse A");
            Product p2 = new Product(2, "Adidas Hoodie", 59.90,Category.TEXTILE, 30, "Warehouse B");
            Product p3 = new Product(3, "Puma Running Shoes", 89.99,Category.FOOTWEAR  , 20, "Warehouse C");

            oos.writeObject(new Order(101, 1, LocalDate.of(2026,9,22), LocalDate.of(2026,9,25), true,new ArrayList<Product>(Arrays.asList(p1))));
            oos.writeObject(new Order(102, 3, LocalDate.of(2026,9,23), false,new ArrayList<Product>(Arrays.asList(p2, p3))));
            oos.writeObject(new Order(103, 5, LocalDate.of(2026,9,20), LocalDate.of(2026,9,28), true,new ArrayList<Product>(Arrays.asList(p3))));
            oos.writeObject(new Order(105, 4, LocalDate.of(2026,9,21), LocalDate.of(2026,9,27), true, new ArrayList<Product>(Arrays.asList(p1, p2))));
            oos.writeObject(new Order(106, 4, LocalDate.of(2026,9,18), LocalDate.of(2026,9,27), false,new ArrayList<Product>(Arrays.asList(p3, p1, p2))));
            
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
                    ArrayList<Product> aProd = o.getaProducts();

                    for (Product pro : aProd) {
                        if (pro.getId() == id) {
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
    
    public void placeOrder(File fichO, Order order) {
        ObjectOutputStream oos = null;
        MyObjectOutputStream moos = null;
        
        if (fichO.exists()) {
            try {
                moos = new MyObjectOutputStream(new FileOutputStream(fichO, true));
                moos.writeObject(order);
                moos.close();
            } catch (FileNotFoundException e) {
                    System.out.println("Error, fichero no encontrado");
            } catch (IOException e) {
                    System.out.println("Error en la entrada de datos");
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(fichO));
                oos.writeObject(order);
                oos.close();
            } catch (FileNotFoundException e) {
                    System.out.println("Error, fichero no encontrado");
            } catch (IOException e) {
                    System.out.println("Error en la entrada de datos");
            }
        }
    }
}
