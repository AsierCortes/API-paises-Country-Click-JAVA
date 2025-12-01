import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Buscador {
	private Set<Info_Pais> paisesBuscados; // SET ya que no quiero que se repita
	private Set<Info_Pais> paisesFavoritos;

	private HttpClient cliente;
	private ObjectMapper objmapper;
	private PaisTranslation paisTranslation;

	// CACHÉ -> Aqui es donde se va a guardar el código el nombre en español y el
	// pais (Instancia)
	private Map<String, Info_Pais> memoriaCache;

	public Buscador() {
		this.paisesBuscados = new HashSet<Info_Pais>();
		this.paisesFavoritos = new HashSet<Info_Pais>();
		this.memoriaCache = new HashMap<String, Info_Pais>();
		this.cliente = HttpClient.newHttpClient();
		this.objmapper = new ObjectMapper();
		this.paisTranslation = new PaisTranslation();
	}

	/*
	 * Parámetro: pais -> Es el pais del que se desea buscar información Retorno: Se
	 * va a devolver un objeto pais
	 * 
	 * 1. Trasladar el nombre del pais en español a Ingles 2. Comprobamos si se
	 * encuentra guardado en el caché 3. Tenemos que hacer la llamada 4. Comprobar
	 * que la llamada devuelve resultados correctos. Si es así guardamos la
	 * respuesta de lo contrario devolvemos null 5. Mappear elobjeto 6. Guardar el
	 * objeto en el map 7. Y devolverlo
	 * 
	 * 
	 * url:
	 * https://restcountries.com/v3.1/name/{nombrePais}?fields={field},{field},{
	 * field}
	 */
	public Info_Pais buscarInfoPais(String pais) {
		try {
			// 1
			String traduccion = paisTranslation.traducirPais(pais);
			
			// Si se ha encontrado
			if (traduccion != null) {

				// 2
				if (memoriaCache.containsKey(traduccion)) {
					return memoriaCache.get(traduccion);
				} else {

					// Tenemos que añadir al final unicamente el nombre del pais en ingles
					StringBuilder urlModificar = new StringBuilder("https://restcountries.com/v3.1/name/");
					urlModificar.append(traduccion);

					String url = new String(urlModificar);
					HttpRequest consulta = HttpRequest.newBuilder().uri(URI.create(url)).build();
					HttpResponse<String> respuestaConsulta = cliente.send(consulta, BodyHandlers.ofString());

					// Recuperamos la respuesta de la consulta
					int respuestaServidor = respuestaConsulta.statusCode();

					if (respuestaServidor == 200) {
						// ÉXITO
						// Hay que crear un new TyperReference
						List<Info_Pais> paises = objmapper.readValue(respuestaConsulta.body(),
								new TypeReference<List<Info_Pais>>() {
								});

						// GUARDAMOS EN EL CACHÉ. Nombre del pais minusculas (Ya viene en minúscula) y
						// la propia instancia
						memoriaCache.put(traduccion, paises.get(0));
						paisesBuscados.add(paises.get(0)); // Guardamos el pais en buscados
						return paises.get(0); // Returnamos el primero, que es el unico pais buscado
					} else {
						// NO ENCONTRADO O ERROR SERVIDOR
						return null;
					}
				}

			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void aniadirFavoritos(Info_Pais pais) {
		paisesFavoritos.add(pais);
	}

	/*
	 * Recibe como parámetro el path donde se creará el archivo, o donde se
	 * guardará, y también recibirá una lista de nombres de los paises en español que se desea
	 * guardar
	 * 
	 * devuelve un String -> Mensaje que informa si se ha podido guardar
	 * correctamente
	 * 
	 * 1. Creamos un objeto File con dicha ruta 
	 * 2. Comprobamos que se pueda leer dicha ruta 
	 * 		2.1 Si se puede continuamos 
	 * 		2.2 De lo contrario devolvemos un mensaje 
	 * 3. Guardamos en una lista los objetos 
	 * 		3.1 Vemos si esta en el cache (IMPORANTRE pais en ingles)
	 * 		3.2 Si no llamamos al metodo
	 * 4. Serializamos la lista de Paises 
	 * 		4.1 Si se ha hecho correctamente mandamos un mensaje como ("Se ha guardado la inforamción")
	 * 
	 * 
	 * 
	 */
	public String guardarInfoFichero(String pathAbsoluto, ArrayList<String> paises) {
		try {
			// 1.
			File fichero = new File(pathAbsoluto);
			// 2.
			if (fichero.canRead() && fichero.canWrite()) {
				ArrayList <Info_Pais> paisesGuardar = new ArrayList<Info_Pais>();
				// 3.
				for (String nombre : paises) {
					System.out.println("Memoria caché: " + memoriaCache);
					String traduccion = paisTranslation.traducirPais(nombre);
					
					if(memoriaCache.containsKey(traduccion)) {
						System.out.println("Esta en la memoria caché");
						Info_Pais pais = memoriaCache.get(traduccion);
						paisesGuardar.add(pais);
					}else {
						System.out.println("No esta en la memoria caché");
						System.out.println("Se va a realizar una petición");
						Info_Pais pais = buscarInfoPais(nombre);
						paisesGuardar.add(pais);
					}
					
				}
				System.out.println("Paises a guardar: " + paisesGuardar);
				
				// 4. 
				ObjectOutputStream serializarPaises = new ObjectOutputStream(new FileOutputStream(fichero));
				serializarPaises.writeObject(paisesGuardar);
				return "Se ha guardado la informacion de los paises en tu dispositivo";
			} else {
				return "No se ha podido acceder al fichero";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
