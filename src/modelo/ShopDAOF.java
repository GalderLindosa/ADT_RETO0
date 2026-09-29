/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ire22
 */
public interface ShopDAOF {
    public void fillDataOrder(File fichO);
    public boolean existCustomer(File fichO, int id);
    public ArrayList<Order> pedidosCliente(File fichO, int id);
    public ArrayList<Order> productos(File fichO, int id) ;
}
