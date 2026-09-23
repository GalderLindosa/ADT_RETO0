/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import utilidades.Utilidades;

/**
 *
 * @author ire22
 */
public class MainView {
   // private ShopControlador controlador;
    public MainView() {
      //  this.controlador= new ShopControlador();
    }
    
    
     public int menu(){
        int resp;
        System.out.println("-1.Registrar producto"
                + "\n-2.Registrar cliente"
                + "\n-3.Consultar stock productos"
                + "\n-4.Editar stock"
                + "\n-5.Realizar pedido"
                + "\n-6.Conusltar pedidos"
                + "\n-7.Ver historial pedido productos"
                + "\n-0.Salir");
                
        resp=Utilidades.leerInt(0, 7); 
        return resp; 
    }
     
     public void start(){
         int option = -1;
         while (option != 0){
             option = this.menu();
         }
         switch (option){
            // case 1: contcon
         }
     }
}
