/*	Aqui solo se va a mostrar la información. Las llamadas, y todo eso se van 
 * 	a realizar en otra clase.
 * 
 */
package Aplicacion;

import java.util.HashSet;
import java.util.Set;

public class Aplicacion_Viajes {

	public static void main(String[] args) {
		Buscador buscador = new Buscador();
	
		Set<Info_Pais> paisesGuardar = new HashSet<Info_Pais>();
		System.out.println(buscador.buscarInfoPais("portugal"));
		paisesGuardar.add(buscador.buscarInfoPais("españa"));
		System.out.println(buscador.buscarInfoPais("portugal"));
		paisesGuardar = buscador.getPaisesHistorial();
		
		System.out.println("Paises historial: " + buscador.getPaisesHistorial());
		if(buscador.buscarInfoPais("ITAholaLIA") == null) {
			System.out.println("no se ha encontrado el pais");
		}
		System.out.println(buscador.guardarInfoFicheroSerializar("C:\\Users\\asier\\Downloads\\hola.txt", paisesGuardar));	
		System.out.println(buscador.deserializarInfoFichero("C:\\Users\\asier\\Downloads\\hola.txt"));
		
//		System.out.println(buscador.conversionDinero(1.2, "eusro", "dolar"));	
//		System.out.println(buscador.getConversionesRealizadas());

	}
	
	

}
