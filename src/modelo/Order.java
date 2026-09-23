/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author ire22
 */
public class Order {
    private int id;
    private int idCostumer;
    private LocalDate orderDate; 
    private LocalDate endDate; 
    private boolean delivered;
    private ArrayList<Integer>aProducts;
    

    public Order(int id,int idCostumer, LocalDate orderDate, LocalDate endDate, boolean delivered,ArrayList<Integer>aProducts) {
        this.id = id;
        this.idCostumer=idCostumer;
        this.orderDate = orderDate;
        this.endDate = endDate;
        this.delivered = delivered;
        this.aProducts=aProducts;
    }
    public Order(int id,int idCostumer, LocalDate orderDate, boolean delivered,ArrayList<Integer>aProducts) {
        this.id = id;
        this.orderDate = orderDate;
        this.delivered = delivered;
         this.aProducts=aProducts;
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public int getIdCostumer() {
        return idCostumer;
    }

    public void setIdCostumer(int idCostumer) {
        this.idCostumer = idCostumer;
    }

    public ArrayList<Integer> getaProducts() {
        return aProducts;
    }

    public void setaProducts(ArrayList<Integer> aProducts) {
        this.aProducts = aProducts;
    }
    

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", endDate=" + endDate + ", delivered=" + delivered + '}';
    }

}
