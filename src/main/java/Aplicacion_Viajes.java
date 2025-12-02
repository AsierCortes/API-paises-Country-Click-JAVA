/*	Aqui solo se va a mostrar la información. Las llamadas, y todo eso se van 
 * 	a realizar en otra clase.
 * 
 */

import java.util.ArrayList;

public class Aplicacion_Viajes {

	public static void main(String[] args) {
		Buscador buscador = new Buscador();
	
		ArrayList<Info_Pais> paisesGuardar = new ArrayList<Info_Pais>();
		System.out.println(buscador.buscarInfoPais("ITALIA"));
		paisesGuardar.add(buscador.buscarInfoPais("ITALIA"));
		
		
		System.out.println(buscador.guardarInfoFichero("C:\\Users\\asier\\Downloads\\hola.txt", paisesGuardar));	
		

	}
	
	

}
