package vista;

import controlador.ShopControlador;
import utilidades.Utilidades;

/**
 *
 * @author ire22
 */
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

        int option;

        do {

            option = menu();

            switch (option) {

                case 0:
                    System.out.println("GOODBYE");
                    break;

                case 1:
                    // controlador.registrarProducto();
                    break;

                case 2:
                    // controlador.registrarCliente();
                    break;

                case 3:
                    // controlador.verProductos();
                    break;

                case 4:
                    controlador.editStock();
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;
            }

        } while (option != 0);
    }
}