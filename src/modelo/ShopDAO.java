package modelo;

public interface ShopDAO {

    // Productos
    
    public void editStock();

    public boolean productExists(Product product);

    public void verProductos();
    public boolean registrarProducto(Product p);

    // Clientes
    public Client getCustomerById(int id);
    public boolean registrarCliente(Client c);
}
