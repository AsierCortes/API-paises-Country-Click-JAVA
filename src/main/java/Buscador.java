import java.io.IOException;
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
	private Set<Info_Pais> paisesBuscados;		// SET ya que no quiero que se repita
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
						paisesBuscados.add(paises.get(0));	// Guardamos el pais en buscados
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
}
