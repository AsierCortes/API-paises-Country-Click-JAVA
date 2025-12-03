/*	Aqui solo se va a mostrar la información. Las llamadas, y todo eso se van 
 * 	a realizar en otra clase.
 * 
 */
package Aplicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Aplicacion_Viajes {

	public static void main(String[] args) {
		Buscador buscador = new Buscador();
		saludar();
		
		

	}
	
	static void saludar() {
		try {
		for (int i = 0; i < 50; i++) {
				System.out.println("Cargando aplicación........");
				Thread.sleep(100);
		}
		System.out.println(" ");
		System.out.println("--------------------------------------------------------");
		System.out.println("APLICACIÓN CARGADA: Asistente de Viajes");
		
		Thread.sleep(1000);
		System.out.println("¡Hola! Soy tu asistente virtual :) ");
		Thread.sleep(2000);
		System.out.println("Estoy aquí para ayudarte y facilitarte la búsqueda");
		System.out.println("de tus próximas vacaciones.");
		
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	

}
