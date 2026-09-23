/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 *
 * @author ire22
 */
public class ImplementacionFichero {
    public static void fillDataOrder(File fichO) {
		ObjectOutputStream oos =null;
                LocalDate orderDate = LocalDate.of(2026, 9, 22);
                LocalDate endDate = LocalDate.of(2026, 9, 30);
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichO));

			//oos.writeObject(new Order(321,546,orderDate,endDate,true) );
			//oos.writeObject(new Order(123,675,orderDate,false));
	
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}    
}
