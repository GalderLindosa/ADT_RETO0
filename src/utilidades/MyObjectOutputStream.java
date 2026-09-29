package utilidades;


import java.io.*;

public class MyObjectOutputStream extends ObjectOutputStream{
	 //Sobrescribimos el m�todo que crea la cabecera
	 protected void writeStreamHeader() throws IOException
	 {
		 	reset();
	 }
	 //Constructores
	 public MyObjectOutputStream () throws IOException{
		 super();
	 }
	 public MyObjectOutputStream(OutputStream out) throws IOException
	 {
		 super(out);
	 }
}