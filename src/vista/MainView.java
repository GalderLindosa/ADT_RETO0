package vista;

import controlador.ShopControlador;
import modelo.*;
import utilidades.Utilidades;
import java.io.File;
import java.util.ArrayList;

public class MainView {

    private ShopControlador controlador;

    public MainView() {
        this.controlador = new ShopControlador();
    }

    public int menu() {

        System.out.println("1. Registrar producto");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Consultar stock productos");
        System.out.println("4. Editar stock");
        System.out.println("5. Realizar pedido");
        System.out.println("6. Consultar pedidos");
        System.out.println("7. Ver historial pedido productos");
        System.out.println("0. Salir");

        return Utilidades.leerInt(0, 7);
    }

    public void start() {

        ImplementacionFichero instanceF = ImplementacionFichero.getInstance();
        ImplementacionBD instanceBD = ImplementacionBD.getInstance();
        File fichO = new File("order.dat");

        int option = -1;

        while (option != 0) {

            option = this.menu();

            switch (option) {

                case 0:
                    System.out.println("GOODBYE");
                    break;

                case 1:
                    controlador.registrarProducto();
                    break;

                case 2:
                    controlador.registrarCliente();
                    break;

                case 3:
                    controlador.verProductos();
                    break;

                case 4:
                    controlador.editStock();
                    break;

                case 5:
                    // controlador.realizarPedido();
                    break;

                case 6:
                    consultarPedidos(instanceF, fichO);
                    break;

                case 7:
                    historialProducto(instanceF, fichO);
                    break;
            }
        }
    }

    // -------------------- CONSULTAR PEDIDOS --------------------
    private void consultarPedidos(ImplementacionFichero instance, File fichO) {

        int idCustomer;
        boolean existe;

        if (!fichO.exists()) {
            instance.fillDataOrder(fichO);
        }

        do {
            System.out.println("Enter the customer ID:");
            idCustomer = Utilidades.leerInt();
            existe = instance.existCustomer(fichO, idCustomer);

            if (!existe) {
                System.out.println("ID not found, please enter it again:");
            }

        } while (!existe);

        ArrayList<Order> orders = instance.pedidosCliente(fichO, idCustomer);

        System.out.println("Orders of the customer with ID: " + idCustomer);
        for (Order o : orders) {
            System.out.println(o.getId());
        }

        boolean correcto = false;
        int pedido;

        do {
            System.out.println("Which order do you want to check?");
            pedido = Utilidades.leerInt();

            for (Order o : orders) {
                if (o.getId() == pedido) {
                    System.out.println(o);
                    correcto = true;
                }
            }

            if (!correcto) {
                System.out.println("Incorrect order ID, try again.");
            }

        } while (!correcto);
    }

    // -------------------- HISTORIAL PRODUCTO --------------------
    private void historialProducto(ImplementacionFichero instance, File fichO) {

        int idPro;
        boolean correcto = false;

        do {
            System.out.println("Enter the product ID:");
            idPro = Utilidades.leerInt();

            ArrayList<Order> productHistory = instance.productos(fichO, idPro);

            if (productHistory == null || productHistory.isEmpty()) {
                System.out.println("Error: The product ID is invalid or there are no orders for this product.");
                correcto = false;
            } else {
                correcto = true;
                System.out.println("Orders containing product " + idPro + ":");
                for (Order o : productHistory) {
                    System.out.println(o.getId());
                }
            }

        } while (!correcto);
    }
}
