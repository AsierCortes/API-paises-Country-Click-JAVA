/*	Aqui solo se va a mostrar la información. Las llamadas, y todo eso se van 
 * 	a realizar en otra clase.
 * 
 */

import java.util.ArrayList;

public class App_TravelHelp {

	public static void main(String[] args) {
		Buscador buscador = new Buscador();
		
		System.out.println(buscador.buscarInfoPais("italia")); 
		System.out.println(buscador.buscarInfoPais("italia")); 
		ArrayList<String> paisesGuardar = new ArrayList<String>();
		paisesGuardar.add("España");
		paisesGuardar.add("Italia");
		
		buscador.guardarInfoFichero("C:\\Users\\asier\\Downloads\\hola.txt", paisesGuardar);
		

	}
	
	

}
