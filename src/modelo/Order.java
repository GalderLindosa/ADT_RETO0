/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author ire22
 */
public class Order {
    private int id;
    private LocalDate orderDate; 
    private LocalDate endDate; 
    private boolean delivered; 

    public Order(int id, LocalDate orderDate, LocalDate endDate, boolean delivered) {
        this.id = id;
        this.orderDate = orderDate;
        this.endDate = endDate;
        this.delivered = delivered;
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

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", endDate=" + endDate + ", delivered=" + delivered + '}';
    }

}
