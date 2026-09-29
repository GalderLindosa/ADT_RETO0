package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import modelo.*;
import utilidades.Utilidades;

public class ShopControlador {

    private ImplementacionBD dao;
    private ImplementacionFichero daoF;

    // -------------------- CONSTRUCTOR --------------------

    public ShopControlador() {
        this.dao = ImplementacionBD.getInstance();
        this.daoF = ImplementacionFichero.getInstance();
    }

    // -------------------- PRODUCTOS BD --------------------

    public void verProductos() {
        dao.verProductos();
    }

    public boolean registrarProducto(Product p) {
        return dao.registrarProducto(p);
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
                p.setCategory(Category.FOOTWEAR);
                break;

            case 2:
                p.setCategory(Category.TEXTILE);
                break;

            case 3:
                p.setCategory(Category.ACCESSORY);
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

    // -------------------- CLIENTES BD --------------------

    public boolean registrarCliente(Client c) {
        return dao.registrarCliente(c);
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

    public Client getCustomerById(int id) {
        return dao.getCustomerById(id);
    }

    // -------------------- STOCK --------------------

    public void editStock() {
        dao.editStock();
    }

    public boolean productExists(Product product) {
        return dao.productExists(product);
    }

    // -------------------- FICHEROS (ORDERS) --------------------

    public void fillDataOrder(File fichO) {
        daoF.fillDataOrder(fichO);
    }

    public ArrayList<Order> pedidosCliente(File fichO, int id) {
        return daoF.pedidosCliente(fichO, id);
    }

    public ArrayList<Order> productos(File fichO, int id) {
        return daoF.productos(fichO, id);
    }

    public static boolean existCustomer(File fichO, int id) {

        boolean clienteExiste = false;
        boolean finArchivo = false;

        try (
            ObjectInputStream ois =
                new ObjectInputStream(
                    new FileInputStream(fichO))
        ) {

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

        } catch (FileNotFoundException e) {

            System.out.println("No se encontró el fichero.");

        } catch (ClassNotFoundException e) {

            System.out.println("La clase Objeto no es válida.");

        } catch (IOException e) {

            System.out.println("Error leyendo el fichero.");
        }

        return clienteExiste;
    }
    
    public void placeOrder(File fichO) {
        int id, idCustomer, idP;
        LocalDate orderDate = LocalDate.now();
        boolean delivered = false, fin = false;
        ArrayList<Product> aProducts = null;
        String respuesta;
        
        System.out.println("Enter the orders id:");
        id = Utilidades.leerInt();
        System.out.println("Enter the customers id:");
        idCustomer = Utilidades.leerInt();
        do {
            System.out.println("Select the id of the product that will be in the order:");
            dao.verProductos();
            idP = Utilidades.leerInt();
            aProducts.add(dao.getProduct(idP));
            System.out.println("Do you want to add more products? (Y/N)");
            respuesta = Utilidades.introducirCadena();
            if (respuesta.equalsIgnoreCase("N")) {
                fin = true;
            }
        } while (!fin);
        
        Order order = new Order (id, idCustomer, orderDate, delivered, aProducts);
        daoF.placeOrder(fichO, order);
        System.out.println("Order added.");
    }

    // -------------------- CONSULTAR PEDIDOS --------------------

    public void consultarPedidos(File fichO) {

        int idCustomer;
        boolean existe;

        if (!fichO.exists()) {
            fillDataOrder(fichO);
        }

        do {

            System.out.println("Enter the customer ID:");
            idCustomer = Utilidades.leerInt();

            existe = existCustomer(fichO, idCustomer);

            if (!existe) {
                System.out.println(
                    "ID not found, please enter it again:"
                );
            }

        } while (!existe);

        ArrayList<Order> orders =
            pedidosCliente(fichO, idCustomer);

        System.out.println(
            "Orders of the customer with ID: "
            + idCustomer
        );

        for (Order o : orders) {
            System.out.println(o.getId());
        }

        boolean correcto = false;
        int pedido;

        do {

            System.out.println(
                "Which order do you want to check?"
            );

            pedido = Utilidades.leerInt();

            for (Order o : orders) {

                if (o.getId() == pedido) {
                    System.out.println(o);
                    correcto = true;
                    break;
                }
            }

            if (!correcto) {
                System.out.println(
                    "Incorrect order ID, try again."
                );
            }

        } while (!correcto);
    }

    // -------------------- HISTORIAL PRODUCTO --------------------

    public void historialProducto(File fichO) {

        int idPro;
        boolean correcto = false;

        do {

            System.out.println("Enter the product ID:");
            idPro = Utilidades.leerInt();

            ArrayList<Order> productHistory =
                productos(fichO, idPro);

            if (productHistory == null
                    || productHistory.isEmpty()) {

                System.out.println(
                    "Error: The product ID is invalid "
                    + "or there are no orders for this product."
                );

                correcto = false;

            } else {

                correcto = true;

                System.out.println(
                    "Orders containing product "
                    + idPro + ":"
                );

                for (Order o : productHistory) {
                    System.out.println(o.getId());
                }
            }

        } while (!correcto);
    }
}