package vista;

import controlador.ShopControlador;
import utilidades.Utilidades;

/**
 *
 * @author ire22
 */
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

    // private ShopControlador controlador;
    public MainView() {
        //  this.controlador= new ShopControlador();
    }

    public void start() {
        int option = -1;
        ShopControlador cont = new ShopControlador();

        while (option != 0) {
            option = this.menu();
            switch (option) {
                case 0:
                    System.out.println("GOODBYE");
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    cont.editStock();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }
        switch (option) {
            // case 1: contcon
        }
    }
}
