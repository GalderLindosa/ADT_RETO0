/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.io.File;
import java.util.ArrayList;
import modelo.Client;
import modelo.ImplementacionBD;
import modelo.ImplementacionFichero;
import modelo.Order;
import utilidades.Utilidades;

/**
 *
 * @author ire22
 */
public class MainView {
   // private ShopControlador controlador;
   
    public MainView() {
     // this.controlador= new ShopControlador();
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
         ImplementacionFichero instance = null;
         ImplementacionBD instanceBD=null;
         File fichO=new File("order.dat");
                 
         instance=instance.getInstance();
         instanceBD=instanceBD.getInstance();
         
         int option = -1;
         while (option != 0){
             option = this.menu();
         }
         switch (option){
            case 6:
            int idCustomer, pedido;
            boolean existe, idCorrecto=false; 
            Client client=null; 
            
            ArrayList<Order> aOrders= new ArrayList<>();
            
            if(!fichO.exists()) {
                instance.fillDataOrder(fichO);
            }
            
            do{
                System.out.println("Enter the customer ID:");
                idCustomer=Utilidades.leerInt();
                existe=instance.existCustomer(fichO, option);//metodo para mirar si existe ese cliente 
                
                if(existe){
                    //mirar orders y buscar todos los que tengan ese id
                    //guardar en un arraylist todos los pedidos y enseñar 
                    aOrders=instance.pedidosCliente(fichO, idCustomer);
                    System.out.println("Orders of the customer with ID: "+idCustomer);
                    for (Order a : aOrders) {
                        System.out.println(a.getId());
                    }
                   do{
                    //preguntar cual quiere para enseñarle los productos que tiene dentro 
                   System.out.println("Which order do you want to check?");
                   pedido=Utilidades.leerInt();
                   
                   for(int i=0;i<aOrders.size() || idCorrecto; i++){
                       if(aOrders.get(i).getId()==pedido){
                           idCorrecto=true;
                           System.out.println(aOrders.get(i));
                       }else {
                           idCorrecto=false;
                           System.out.println("The entered order ID is incorrect, please enter it again.");
                       }
                   }
                   }while (!!idCorrecto);
                  }         
                if(!existe){
                    System.out.println("ID not found, please enter it again:");
                }
            }while(!existe);
            
            break;
            case 7:
                int idPro;
                boolean correcto=false;
    //View order history for a specific product(saltar el popUp)
    //preguntar cual quiere 
    //miro en todos los orders entro en sus arraylist y miro si esta ese producto (no se ha enocntrado o no se encuentra)
    //me lo guardo en un arrayList y lo enseño 
               do{
                System.out.println("Enter the product ID:");
                idPro=Utilidades.leerInt();
              
                ArrayList<Order> productHistori=instance.productos(fichO, idPro);
                if(productHistori==null){
                   correcto=false; 
                }else{
                   correcto=true;
                for (Order a : productHistori) {
                        System.out.println(a.getId());
                }
                
               }
                if(!correcto){
                    System.out.println("Error: The product ID is invalid or there are no orders for this product.");
                }
                
               }while(!correcto);
            break; 
           }
         }
         
        
   
     }

