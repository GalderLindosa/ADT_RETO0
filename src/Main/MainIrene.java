/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import modelo.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 *
 * @author ire22
 */
public class MainIrene {
        public static void fillDataOrder(File fichO) {
		ObjectOutputStream oos =null;
                LocalDate orderDate = LocalDate.of(2026, 9, 22);
                LocalDate endDate = LocalDate.of(2026, 9, 30);


		Order o1 = new Order(321,orderDate,endDate,true);
		Object o2 = new Order(123,orderDate,false);
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichO));

			oos.writeObject(new Order(321,orderDate,endDate,true) );
			oos.writeObject(new Order(123,orderDate,false));
			
			ArrayList <Objeto> Objetos1= new ArrayList<Objeto>();
			ArrayList <Objeto> Objetos2= new ArrayList<Objeto>();
			ArrayList <Objeto> Objetos3= new ArrayList<Objeto>();
			ArrayList <Objeto> Objetos4= new ArrayList<Objeto>();

			Objetos1.add((Objeto) o1);
			Objetos1.add((Objeto) o2);
			Objetos2.add((Objeto) o3);
			Objetos2.add((Objeto) o4);
			Objetos3.add((Objeto) o5);
			Objetos3.add((Objeto) o6);
			Objetos4.add((Objeto) o6);

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
