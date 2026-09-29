/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

/**
 *
 * @author Unai.Ibarguren
 */
public interface ShopDAO {

    
    public void editStock();

    public boolean productExists(Product product);

    public void verProductos();

    public Client getCustomerById(int id);

    public boolean registrarProducto(Product p);

    public boolean registrarCliente(Client c);

    public void editStock();

    public boolean productExists(Product product);
}