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
import utilidades.Utilidades;

/**
 *
 * @author Unai.Ibarguren
 */
public class ShopControlador {

    private ImplementacionBD dao;

    public void verProductos() {
        dao.verProductos();
    }

    public ShopControlador() {
        this.dao = ImplementacionBD.getInstance();
    }

    public Client getCustomerById(int id) {
        return dao.getCustomerById(id);
    }

    public boolean registrarProducto(Product p) {
        return dao.registrarProducto(p);
    }

    public boolean registrarCliente(Client c) {
        return dao.registrarCliente(c);
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

    public void registrarProducto() {

        Product p = new Product();

        System.out.println("Insert the ID of the product:");
        p.setId(Utilidades.leerInt());

        System.out.println("Insert name:");
        p.setName(Utilidades.introducirCadena());

        System.out.println("Insert price:");
        p.setPrice(Utilidades.leerDouble());

        System.out.println("Insert the category:");
        System.out.println("1. Footwear");
        System.out.println("2. Textiles");
        System.out.println("3. Accessories");

        int opcionCategoria = Utilidades.leerInt(1, 3);

        switch (opcionCategoria) {
            case 1:
                p.setCategory(Category.Footwear);
                break;

            case 2:
                p.setCategory(Category.Textiles);
                break;

            case 3:
                p.setCategory(Category.Accessories);
                break;
        }

        System.out.println("Insert stock:");
        p.setStock(Utilidades.leerInt());

        System.out.println("Insert the path:");
        p.setPath(Utilidades.introducirCadena());

        boolean resultado = dao.registrarProducto(p);

        if (resultado) {
            System.out.println("Product registered correctly.");
        } else {
            System.out.println("Error registering a product.");
        }
    }

    public void registrarCliente() {

        Client c = new Client();

        System.out.println("Insert the ID of client:");
        c.setId(Utilidades.leerInt());

        System.out.println("Insert the name:");
        c.setName(Utilidades.introducirCadena());

        System.out.println("Insert the email:");
        c.setEmail(Utilidades.introducirCadena());

        System.out.println("Insert phone number:");
        c.setPhoneNumber(Utilidades.introducirCadena());

        System.out.println("Insert address:");
        c.setAddress(Utilidades.introducirCadena());

        boolean resultado = dao.registrarCliente(c);

        if (resultado) {
            System.out.println("Client registered correctly.");
        } else {
            System.out.println("Error registering a client.");
        }
    }

}
