/*	Aqui solo se va a mostrar la información. Las llamadas, y todo eso se van 
 * 	a realizar en otra clase.
 * 
 */
package Aplicacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Aplicacion_Viajes {

	public static void main(String[] args) {
		Buscador buscador = new Buscador();
		Scanner sc = new Scanner(System.in);
		// Instanciamos tu validador
		CodigosValidosMonedas validadorMoneda = new CodigosValidosMonedas();
		PaisTraduccion traductor = new PaisTraduccion();
		saludar();

		System.out.println(
				"Para empezar a buscar tus vacaciones, vamos a buscar información sobre el país al que deseas viajar ✈️.");
		System.out.println("Por favor, introduce el nombre del país que deseas buscar:");

		Info_Pais paisBuscado = null;

		// El bucle se repite mientras la variable siga vacía (null)
		while (paisBuscado == null) {
			System.out.print(">> ");
			String paisIntroducir = sc.nextLine();

			// 1. Comprobamos si ha escrito algo válido (no está vacío)
			if (!paisIntroducir.trim().isEmpty()) {
				try {
					// Intentamos buscar
					paisBuscado = buscador.buscarInfoPais(paisIntroducir);

					// 2. Si ha buscado pero devuelve null, avisamos del error
					if (paisBuscado == null) {
						System.out.println("❌ No he encontrado información sobre '" + paisIntroducir + "'.");
						System.out.println("Puede que esté mal escrito. Inténtalo de nuevo:");
					}

				} catch (InputMismatchException e) {
					System.err.println(
							"Solo puedes introducir el nombre de un pais, no es valido números ni caracteres especiales");
					sc.nextLine();
				} catch (Exception e) {
					System.err.println("⚠️ Ha ocurrido un error técnico. Inténtalo de nuevo:");

				}

			} else {
				// 3. Si el usuario dio a Enter sin escribir nada
				System.out.println("⚠️ Por favor, escribe un nombre antes de pulsar Enter.");
				System.out.println(" ");
			}
		}
		System.out.println(" ");
		// Al salir del while, garantizamos que paisBuscado tiene datos
		System.out.println(
				"✅ ¡Destino localizado! Cargando datos de " + paisBuscado.getNombre().getNombreComun() + "...");

		// ==============================================
		// MOSTRAR INFO:

		// 1. LÓGICA DE CLASIFICACIÓN (Lo que pediste)
		// 1. LÓGICA DE CLASIFICACIÓN
		String tamanoPoblacion;
		int habitantes = paisBuscado.getPoblacion();

		if (habitantes > 50_000_000) {
			tamanoPoblacion = "País muy poblado (Mucha actividad)";
		} else if (habitantes > 10_000_000) {
			tamanoPoblacion = "Población grande";
		} else if (habitantes > 1_000_000) {
			tamanoPoblacion = "Población media";
		} else {
			tamanoPoblacion = "Población pequeña (Tranquilo)";
		}

		// 2. PREPARACIÓN DE DATOS (Para quitar corchetes y poner puntos de miles)
		String idiomasLimpios = String.join(", ", paisBuscado.getLenguajes().values());
		String pobFormat = String.format("%,d", habitantes);

		// 3. IMPRESIÓN LIMPIA
		System.out.println("\n✈️   DESTINO SELECCIONADO: " + paisBuscado.getNombre().getNombreComun().toUpperCase());
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

		System.out.println("  📍  Capital:       " + paisBuscado.getCapital().get(0) + "(Tu punto de aterrizaje)");
		System.out.println("");

		System.out.println(
				"  💰  Moneda:        " + paisBuscado.getNombreMoneda() + " (" + paisBuscado.getSimboloMoneda() + ")");
		System.out.println("  🗣️  Idioma:        " + idiomasLimpios);
		System.out.println("");

		System.out.println("  🌍  Región:        " + paisBuscado.getRegion());
		System.out.println("  🕒  Huso Horario:  " + paisBuscado.getZonaHoraria().get(0));
		System.out.println("");

		System.out.println("  👥  Población:     " + pobFormat + " habitantes    -> " + tamanoPoblacion);
		System.out.println(
				"──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────\n");

		System.out.println(" ");
		System.out.print("¿Deseas guardar en favoritos la información de este pais? (Si / No): ");

		Set<Info_Pais> paisesHistorial = buscador.getPaisesHistorial();
		Set<Info_Pais> paisesFavoritos = buscador.getPaisesFavoritos();

		boolean opcionValida = false;

		while (!opcionValida) {
			System.out.print(">> ");
			String decision = sc.nextLine();

			if (decision.equalsIgnoreCase("si")) {

				buscador.aniadirFavoritos(paisBuscado);

				System.out.println("Se va a proceder a mostrar los paises que tienes guardados en favoritos...");
				mostrarPaisesFavoritos(paisesFavoritos, traductor);
				System.out.println("Llevas buscados los siguientes paises");
				mostrarHistorial(paisesHistorial, traductor);

				// Marcamos como válida para salir del bucle
				opcionValida = true;

			} else if (decision.equalsIgnoreCase("no")) {
				System.out.println("❌ No se ha guardado " + paisBuscado.getNombre() + " en favoritos");
				System.out.println("Se va a proceder a mostrar el historial de paises buscados.");
				mostrarHistorial(paisesHistorial, traductor);

				// Marcamos como válida para salir del bucle
				opcionValida = true;

			} else {
				// Si el usuario escribe otra cosa
				System.err.println("⚠️ Por favor, introduce únicamente si o no");
			}
		}

		// CAMBIO DE MONEDA

		System.out.println("\n------------------------------------------------");
		System.out.println(" 💱  OFICINA DE CAMBIO DE DIVISAS");
		System.out.println("------------------------------------------------");

		System.out.println("Ahora vamos a proceder a hacer un cambio de divisas, así vas a poder saber cuanto dinero ");
		System.out.println("tienes al pais que deseas irte de vacaciones " + paisBuscado.getNombre().getNombreComun()
				+ " usa la siguiente moneda: ");
		System.out.println(paisBuscado.getNombreMoneda() + " -> " + paisBuscado.getSimboloMoneda());

		// Variable interruptor para controlar el bucle
		boolean cambioRealizado = false;

		while (!cambioRealizado) {
			System.out.println("🪙 Introduce tu moneda actual: (Ejemplo: Soy de España, pues el \"euro\")");
			System.out.print(">> ");
			String monedaLocal = sc.nextLine();

			System.out.println("🪙 Introduce tu moneda a la que deseas realizar el cambio: ");
			System.out.print(">> ");
			String monedaDestino = sc.nextLine();

			System.out.println("💵 Introduce la cantidad de dinero: ");
			System.out.print(">> ");

			// Usamos parseDouble con nextLine para evitar saltos de línea erróneos al
			// repetir el bucle
			double cantidadDinero = 0;
			try {
				cantidadDinero = Double.parseDouble(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("⚠️ Debes introducir un número válido.");
			}

			// Intentamos la conversión
			double resultadoConversion = buscador.conversionDinero(cantidadDinero, monedaLocal, monedaDestino);

			if (resultadoConversion <= 0) {
				// CASO DE ERROR: Avisamos y NO cambiamos el interruptor, así el bucle se repite
				System.err.println("⚠️ Error: Una de las monedas no existe o la cantidad es incorrecta.");
				System.out.println("🔄 Por favor, vuelve a introducir los datos correctamente.\n");
				System.out.println("ℹ️ Por si has escrito mal el nombre de la moneda, te dejo por aqui los códigos");
				System.out.println(" ");
				validadorMoneda.getTodosLosCodigosMoneda();
			} else {
				// CASO DE ÉXITO: Mostramos resultado y activamos el interruptor para salir
				System.out.println("✅ Se ha realizado correctamente el cambio");
				System.err.println(
						cantidadDinero + " " + monedaLocal + " = " + resultadoConversion + " " + monedaDestino);

				cambioRealizado = true; // Esto hará que el while termine limpiamente
			}
		}

		// Buscar puntos localizacones capital pais
		System.out.println("\n------------------------------------------------");
		System.out.println(" 🔍  BUSCAR LOCALIZACIONES EN LA CAPITAL");
		System.out.println("------------------------------------------------");

		System.out.println("Esta es la parte DIVERTIDA del viaje. Vamos a buscar lugares ");
		System.out.println("que visitar");

		System.out.println("Tenemos los siguientes lugares disponibles: \n");

		// --- SECCIÓN TRANSPORTE ---
		System.out.println("   ✈️  TRANSPORTE");
		System.out.println("       • 1. Aeropuertos cercanos");

		// --- SECCIÓN COMPRAS ---
		System.out.println("\n   🛍️  CENTROS COMERCIALES Y TIENDAS");
		System.out.println("       • 2. Tiendas de ropa");
		System.out.println("       • 3. Tiendas de comida y alimentación");
		System.out.println("       • 4. Farmacias");
		System.out.println("       • 5. Mercadillos");

		// --- SECCIÓN RESTAURANTES ---
		System.out.println("\n   🍽️  RESTAURANTES Y COMIDA");
		System.out.println("       [Comida Rápida]");
		System.out.println("       • 6. Comida rápida general");
		System.out.println("       • 7. Kebab");
		System.out.println("       • 8. Pizzas");
		System.out.println("       • 9. Tacos");
		System.out.println("       [Por Estilo]");
		System.out.println("       • 10. Restaurante Argentino");
		System.out.println("       • 11. Restaurante Asiático");
		System.out.println("       • 12. Restaurante Brasileño");
		System.out.println("       • 13. Restaurante Japonés");

		// --- SECCIÓN SALUD ---
		System.out.println("\n   🏥  EMERGENCIAS Y SALUD");
		System.out.println("       • 14. Ambulancias");
		System.out.println("       • 15. Hospitales");
		System.out.println("       • 16. Entradas de urgencias");
		System.out.println("       • 17. Podólogos");
		System.out.println("       • 18. Oculistas");

		// --- SECCIÓN ENTRETENIMIENTO ---
		System.out.println("\n   🎭  ENTRETENIMIENTO Y CULTURA");
		System.out.println("       • 19. Cine");
		System.out.println("       • 20. Cultura");
		System.out.println("       • 21. Teatros");
		System.out.println("       • 22. Museos");
		System.out.println("       • 23. Planetarios");
		System.out.println("       • 24. Zoo");
		System.out.println("       • 25. Acuario");
		System.out.println("       [Parques]");
		System.out.println("       • 26. Parque de atracciones");
		System.out.println("       • 27. Parque acuático");

		System.out.println("\n------------------------------------------------");

		System.out.println("Se va a buscar estos lugares en " + paisBuscado.getCapital() + ". Coordenadas -> ");
		System.out.println("- Longuitud: " + paisBuscado.getLongitudCapital());
		System.out.println("- Latitud: " + paisBuscado.getLatitudCapital());

		System.out.println(" ");

		// LONGUITUD Y LATITUD
		double longuitudCapital = paisBuscado.getLongitudCapital();
		double latitudCapital = paisBuscado.getLatitudCapital();

		// COORDENADAS CAPITAL
		ArrayList<Double> coordenadasCapital = new ArrayList<Double>();
		coordenadasCapital.add(longuitudCapital);
		coordenadasCapital.add(latitudCapital);

		// SELECCIÓN CATEGORIA
		System.out.println("Selecciona una categoria que desees buscar: ");
		int categoriaSeleccionada = sc.nextInt();
		sc.nextLine();
		System.out.println(" ");

		String resultadoCategoriaElegida = elegirCategoria(categoriaSeleccionada);
		if (resultadoCategoriaElegida != null) {
			System.out.println("Categoria elegida: " + resultadoCategoriaElegida);
		} else {
			System.out.println("⚠️ Has elegido un número que no se encuentra dentro de las opciones");
		}
		// Lista categoria
		ArrayList<String> listaCategoriasSeleccionadas = new ArrayList<String>();
		listaCategoriasSeleccionadas.add(resultadoCategoriaElegida);

		// Radio de busqueda
		System.out.println("Introduce el radio de búsqueda: (km2) MIN 1km2 MAX 10km2");
		int limiteRadioBusqueda = sc.nextInt();
		sc.nextLine();
		System.out.println(" ");

		StringBuilder radioConvertirm2 = new StringBuilder(String.valueOf(limiteRadioBusqueda));

		if (limiteRadioBusqueda >= 1 && limiteRadioBusqueda <= 10) {
			System.out.println("✅ Limite de radio correcto: " + limiteRadioBusqueda);
			radioConvertirm2.append("000");
		} else {
			System.out.println("❌ Limite de radio inválido, debes introducir un número valido (1-10");
		}
		String radioLimiteString = new String(radioConvertirm2);

		// LIMITE RADIO m2
		int limiteRadioCorrecto = Integer.parseInt(radioLimiteString);

		System.out.println("Introduce la cantidad de resultados que deseas: ");
		// CANTIDAD RESULTADOS
		int cantidadResultados = sc.nextInt();
		sc.nextLine();

		System.out.println(" ");
		System.out.println("---------------------------------------------------------");
		System.out.println("Resumen solicitud busquda de lugares: ");
		System.out.println("- Categoria escogida: " + resultadoCategoriaElegida);
		System.out.println("- Coordenadas: " + coordenadasCapital);
		System.out.println("- Limite Radio: " + limiteRadioBusqueda);
		System.out.println("- Cantidad de resultados: " + cantidadResultados);
		System.out.println("---------------------------------------------------------");
		System.out.println(" ");

		System.out.println("Buscando lugares ......");
		System.out.println(" ");

		ArrayList<Lugares> resultadoLugares = buscador.buscarLugar(coordenadasCapital, limiteRadioCorrecto,
				listaCategoriasSeleccionadas, cantidadResultados);

		// LLAMADA CORRECTA
		if (resultadoLugares != null) {
			System.out.println("✅ Datos de lugares recibidos correctamente ");
		} else if (resultadoLugares.isEmpty()) {
			System.out.println("⚠️ No se ha encontrado lugares con dichas categorías");
		} else {
			System.err.println("⚠️ ERROR, ha habido un fallo en el servidor ");
		}

		System.out.println(" ");

		System.out.println("\n══════════════════════════════════════════════════════");
		System.out.println(" 📍 RESULTADOS DE LA BÚSQUEDA");
		System.out.println("══════════════════════════════════════════════════════");

		System.out.println(" ");

		if (resultadoLugares.isEmpty() == false && resultadoLugares != null) {
			int contadorLugares = 1;

			for (Lugares lugarActual : resultadoLugares) {
				System.out.println("---------------------------------------------------------");
				// Imprimimos el número del lugar
				System.out.println("   #" + contadorLugares);

				// Aseguramos que 'informacion' no sea null para evitar errores
				if (lugarActual.getInformacion() != null) {

					// NOMBRE
					String nombre = lugarActual.getInformacion().getNombre();
					if (nombre != null) {
						System.out.println("   🏛️  NOMBRE:    " + nombre.toUpperCase());
					} else {
						System.out.println("   🏛️  NOMBRE:    (Desconocido)");
					}

					// UBICACIÓN
					String ubicacion = lugarActual.getInformacion().getUbicacion();
					if (ubicacion != null) {
						System.out.println("   🗺️  DIRECCIÓN: " + ubicacion);
					} else {
						System.out.println("   🗺️  DIRECCIÓN: (No disponible)");
					}

					// HORARIO
					String horario = lugarActual.getInformacion().getHorario();
					if (horario != null) {
						System.out.println("   🕒  HORARIO:   " + horario);
					} else {
						System.out.println("   🕒  HORARIO:   (Consultar en local)");
					}
				}

				System.out.println(); // Espacio al final
				contadorLugares++;
			}
		}
		System.out.println("══════════════════════════════════════════════════════\n");
	}

	static String elegirCategoria(int opcion) {
		String categoriaAPI = null;
		switch (opcion) {
		// --- TRANSPORTE ---
		case 1:
			categoriaAPI = "airport";
			break;

		// --- COMPRAS ---
		case 2:
			categoriaAPI = "commercial.clothing";
			break;
		case 3:
			categoriaAPI = "commercial.food_and_drink";
			break;
		case 4:
			categoriaAPI = "commercial.health_and_beauty.pharmacy";
			break;
		case 5:
			categoriaAPI = "commercial.marketplace";
			break;

		// --- RESTAURANTES ---
		case 6:
			categoriaAPI = "catering.fast_food";
			break;
		case 7:
			categoriaAPI = "catering.fast_food.kebab";
			break;
		case 8:
			categoriaAPI = "catering.fast_food.pizza";
			break;
		case 9:
			categoriaAPI = "catering.fast_food.tacos";
			break;
		case 10:
			categoriaAPI = "catering.restaurant.argentinian";
			break;
		case 11:
			categoriaAPI = "catering.restaurant.asian";
			break;
		case 12:
			categoriaAPI = "catering.restaurant.brazilian";
			break;
		case 13:
			categoriaAPI = "catering.restaurant.japanese";
			break;

		// --- SALUD ---
		case 14:
			categoriaAPI = "emergency.ambulance_station";
			break;
		case 15:
			categoriaAPI = "healthcare.hospital";
			break;
		case 16:
			categoriaAPI = "emergency.emergency_ward_entrance";
			break;
		case 17:
			categoriaAPI = "healthcare.podiatrist";
			break;
		case 18:
			categoriaAPI = "healthcare.optometrist";
			break;

		// --- ENTRETENIMIENTO ---
		case 19:
			categoriaAPI = "entertainment.cinema";
			break;
		case 20:
			categoriaAPI = "entertainment.culture";
			break;
		case 21:
			categoriaAPI = "entertainment.culture.theatre";
			break;
		case 22:
			categoriaAPI = "entertainment.museum";
			break;
		case 23:
			categoriaAPI = "entertainment.planetarium";
			break;
		case 24:
			categoriaAPI = "entertainment.zoo";
			break;
		case 25:
			categoriaAPI = "entertainment.aquarium";
			break;
		case 26:
			categoriaAPI = "entertainment.theme_park";
			break;
		case 27:
			categoriaAPI = "entertainment.water_park";
			break;

		default:
			System.err.println("❌ Opción no válida. Por favor elige un número del 1 al 27.");
			return null;

		}
		return categoriaAPI;

	}

	static void leerYMostrarInformacionFichero(String pathAbsoluto) {

	}

	static void mostrarPaisesFavoritos(Set<Info_Pais> paisesFavoritos, PaisTraduccion traduccion) {
		System.out.println(" ");
		System.out.println("Paises guardados en favoritos: ");

		int contador = 1;
		for (Info_Pais infoPais : paisesFavoritos) {
			System.out.println(contador + ". " + infoPais.getNombre().getNombreComun());
			contador++;
		}
		System.out.println(" ");
	}

	static void mostrarHistorial(Set<Info_Pais> paisesHistorial, PaisTraduccion traduccion) {
		System.out.println(" ");
		System.out.println("Historial de paises buscados: ");

		int contador = 1;
		for (Info_Pais infoPais : paisesHistorial) {
			System.out.println(contador + ". " + infoPais.getNombre().getNombreComun());
			contador++;
		}
		System.out.println(" ");
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

//			esperaAleatoria();
			System.out.println("¡Hola! Soy tu asistente virtual :) ");
//			esperaAleatoria();
			System.out.println("Estoy aquí para ayudarte y facilitarte la búsqueda de tus próximas vacaciones.");
//			esperaAleatoria();
			System.out.println("En este chat vamos a ver:");
//			esperaAleatoria();
			System.out.println("   1. ℹ️  Información detallada del país.");
//			Thread.sleep(500);
			System.out.println("   2. 💱  Cálculo y cambio de divisas.");
//			Thread.sleep(700);
			System.out.println("   3. 📍  Buscador de sitios: Restaurantes, cines, hoteles...");
//			esperaAleatoria();
			System.out.println("");
			System.out.println("Vamos a empezar!!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void esperaAleatoria() {
		try {
			long min = 750;
			long max = 3000;

			long tiempoEspera = (long) (Math.random() * (max - min) + min);

			Thread.sleep(tiempoEspera);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
