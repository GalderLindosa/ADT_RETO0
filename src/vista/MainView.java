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
        
        if (!fichO.exists()) {
            instanceF.fillDataOrder(fichO);
        }

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
                   controlador.consultarPedidos(fichO);
                    break;

                case 7:
                   controlador.historialProducto( fichO);
                    break;
            }
        }
    }  
}
