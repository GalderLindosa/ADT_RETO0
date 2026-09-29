package modelo;

public interface ShopDAO {

    // Productos
    public void verProductos();
    public boolean registrarProducto(Product p);
    public boolean productExists(Product product);
    public void editStock();

    // Clientes
    public Client getCustomerById(int id);
    public boolean registrarCliente(Client c);
}
