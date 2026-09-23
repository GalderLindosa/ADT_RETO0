/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author Unai.Ibarguren
 */
public class Client {
    
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address; 

    public Client(int id, String name, String email, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
      public Client() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

  
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + '}';
    }
    
}
