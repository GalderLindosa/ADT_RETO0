package vista;

import controlador.ShopControlador;
import modelo.*;
import utilidades.Utilidades;
import java.io.File;
import java.util.ArrayList;

public class MainView {

    public static int menu() {
        System.out.println("1. Register a product.");
        System.out.println("2. Register a customer.");
        System.out.println("3. Place a product order.");
        System.out.println("4. Edit product stock. ");
        System.out.println("5. Check available products.");
        System.out.println("6. Check a custormer's order.");
        System.out.println("7. View order history for a specific product.");
        System.out.println("Choose an option");
        return Utilidades.leerInt(0, 7);
    }


    public void start() {
       ImplementacionFichero instanceF = ImplementacionFichero.getInstance();
        ImplementacionBD instanceBD = ImplementacionBD.getInstance();
        File fichO = new File("order.dat");
        ShopControlador cont = new ShopControlador();
      if (!fichO.exists()) {
            instanceF.fillDataOrder(fichO);
        }
        int option;
        do {
            option = menu();
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
                   controlador.consultarPedidos(fichO);
                    break;

                case 7:
                   controlador.historialProducto( fichO);
                    break;
            }

        } while (option != 0);
    }
}
