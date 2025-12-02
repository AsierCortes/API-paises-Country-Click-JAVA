package Aplicacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Buscador {
	// SETS ya que no quiero que se repita
	private Set<Info_Pais> paisesHistorial;
	private Set<Info_Pais> paisesFavoritos;

	private Set<ConversionResultado> conversionesRealizadasHistorial;

	private HttpClient cliente;
	private ObjectMapper objmapper;
	private PaisTraduccion paisTraduccion;
	private CodigosValidosMonedas codigosValidosMonedas;

	// CACHÉ -> Se guarda paisEnIngles y el objeto Info_Pais
	private Map<String, Info_Pais> memoriaCache;

	public Buscador() {
		this.paisesHistorial = new HashSet<Info_Pais>();
		this.paisesFavoritos = new HashSet<Info_Pais>();
		this.conversionesRealizadasHistorial = new HashSet<ConversionResultado>();
		this.memoriaCache = new HashMap<String, Info_Pais>();
		this.cliente = HttpClient.newHttpClient();
		this.objmapper = new ObjectMapper();
		this.paisTraduccion = new PaisTraduccion();
		this.codigosValidosMonedas = new CodigosValidosMonedas();
	}

	/*
	 * Parámetros pais -> Nombre en español del pais
	 * 
	 * BuscarInfoPais() -> Recibe el nombre del país en español, lo traduce a ingles
	 * y te devuelve la información (instancia Info_Pais). Cualquier error, o si no
	 * se ha encontrado información sobre ese pais devuelve null
	 * 
	 * url:
	 * https://restcountries.com/v3.1/name/{nombrePais}?fields={field},{field},{
	 * field}
	 */
	public Info_Pais buscarInfoPais(String pais) {
		try {
			// 1 Traducimos a ingles el nomrbe
			String paisEnIngles = paisTraduccion.traducirPais(pais); // Lo devuelve en lowercase

			// Si se ha traducido correctamente
			if (paisEnIngles != null) {

				// 2 Buscamos en el map
				if (memoriaCache.containsKey(paisEnIngles)) {
					System.out.println(paisEnIngles + " -> Se encuentra en la caché");
					return memoriaCache.get(paisEnIngles); // Si esta en el caché devolvemos el objeto (Info_Pais)
					// Si no se encuentra en el map, hay que hacer la llamada a la API
				} else {
					System.out.println(paisEnIngles + " -> NO esta en la caché");

					// Tenemos que añadir al final unicamente el nombre del pais en ingles
					StringBuilder urlModificar = new StringBuilder("https://restcountries.com/v3.1/name/");
					urlModificar.append(paisEnIngles); // Lo añadimos

					String url = new String(urlModificar);

					// Hacemos llamada
					HttpRequest consulta = HttpRequest.newBuilder().uri(URI.create(url)).build();
					HttpResponse<String> respuestaConsulta = cliente.send(consulta, BodyHandlers.ofString());

					// Recuperamos la respuesta de la consulta
					int respuestaServidor = respuestaConsulta.statusCode();

					if (respuestaServidor == 200) {
						// ÉXITO
						// Hay que crear un new TyperReference (Lista de Info_Pais)
						List<Info_Pais> paises = objmapper.readValue(respuestaConsulta.body(),
								new TypeReference<List<Info_Pais>>() {
								});

						// Guardamos en la cache (nombreIngles -> Objeto (Info_Pais))
						memoriaCache.put(paisEnIngles, paises.get(0));
						paisesHistorial.add(paises.get(0)); // Guardamos el pais en historial
						return paises.get(0); // Returnamos el primero, que es el unico pais buscado (instancia ->
												// Paises_Info)
					} else {
						// NO ENCONTRADO O ERROR SERVIDOR
						return null;
					}
				}
				// Si no se ha podido traducir correctamente
			} else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null; // Si ha habido un error
	}

	// Añade a favoritos el pais
	public void aniadirFavoritos(Info_Pais pais) {
		paisesFavoritos.add(pais);
	}

	/*
	 * Recibe como parámetro el path donde se creará el archivo, o donde se
	 * guardará, y también recibirá una lista de nombres de los paises en español
	 * que se desea guardar
	 * 
	 * devuelve un String -> Mensaje que informa si se ha podido guardar
	 * correctamente
	 * 
	 * 1. Creamos un objeto File con dicha ruta 2. Comprobamos que se pueda leer
	 * dicha ruta 2.1 Si se puede continuamos 2.2 De lo contrario devolvemos un
	 * mensaje 3. Guardamos en una lista los objetos 3.1 Vemos si esta en el cache
	 * (IMPORANTRE pais en ingles) 3.2 Si no llamamos al metodo 4. Serializamos la
	 * lista de Paises 4.1 Si se ha hecho correctamente mandamos un mensaje como
	 * ("Se ha guardado la inforamción")
	 * 
	 * 
	 * 
	 */

	public Set<Info_Pais> getPaisesHistorial() {
		return paisesHistorial;
	}

	public Set<Info_Pais> getPaisesFavoritos() {
		return paisesFavoritos;
	}

	public Set<ConversionResultado> getConversionesRealizadasHistorial() {
		return conversionesRealizadasHistorial;
	}

	// pathAbsoluto -> Fichero donde se va a guardar
	// paisesGuardar -> Lista de Info_Pais (Objetos)

	/*
	 * guardarInfoFichero() -> Se recibe una ruta, se crea o se sobreescribe en ese
	 * fichero y se guarda la lista Info_Pais (Instancias)
	 * 
	 */
	public String guardarInfoFicheroSerializar(String pathAbsoluto, Set<Info_Pais> paisesGuardar) {
		try {
			// 1. Creamos fichero
			File fichero = new File(pathAbsoluto);
			// 2. Vemos si se puede leer y escribir
			if (fichero.canRead() && fichero.canWrite()) {

				// Guardamos la lista
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

	/*
	 * Parametro: - pathAbsoluto -> Fichero donde se encuentra objetos serializados
	 * 
	 * deserializarInfoFichero() -> a través del path absoluto deserializa los
	 * objetos que hay en ese fichero. Una vez deserializado devuelve el set. Si no
	 * ha podido deserializarlo devuelve null
	 * 
	 */
	public Set<Info_Pais> deserializarInfoFichero(String pathAbsoluto) {
		try {
			// 1. Creamos fichero
			File fichero = new File(pathAbsoluto);
			// 2. Vemos si se puede leer y escribir
			if (fichero.canRead() && fichero.canWrite()) {
				ObjectInputStream deserializarPaises = new ObjectInputStream(new FileInputStream(fichero));
				Set<Info_Pais> infoExportada = (Set<Info_Pais>) deserializarPaises.readObject();
				return infoExportada;
			} else {
				return null;
			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Parametros: - dinero -> Dinero que hay convertir - monedaOrigen -
	 * monedaDestino
	 * 
	 * conversionDinero() -> recibe dinero, monedaOrigen y monedaDestino, comprueba
	 * que son códigos de monedas correctas (a través del map) y realiza la llamada,
	 * si todo va correcto devuelve el resultado.
	 * 
	 * https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/pair/EUR/USD/3.24
	 */
	public double conversionDinero(double dinero, String monedaOrigen, String monedaDestino) {
		try {
			String codigoOrigen = codigosValidosMonedas.devolverCodigoMoneda(monedaOrigen);
			String codigoDestino = codigosValidosMonedas.devolverCodigoMoneda(monedaDestino);
			// Si se ha encontrado bien el codigo
			if (codigoOrigen != null && codigoDestino != null) {
				StringBuilder urlModificada = new StringBuilder(
						"https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/pair/");
				urlModificada.append(codigoOrigen + "/");
				urlModificada.append(codigoDestino + "/");
				urlModificada.append(String.valueOf(dinero));

				String url = new String(urlModificada);

				// Realizar llamada
				HttpRequest consulta = HttpRequest.newBuilder().uri(URI.create(url)).build();
				HttpResponse<String> respuestaConsulta = cliente.send(consulta, BodyHandlers.ofString());

				// Recuperamos la respuesta de la consulta
				int respuestaServidor = respuestaConsulta.statusCode();

				if (respuestaServidor == 200) {
					// ÉXITO
					ConversionResultado conversion = objmapper.readValue(respuestaConsulta.body(),
							ConversionResultado.class);
					conversionesRealizadasHistorial.add(conversion);
					return conversion.getResultado();
				} else {
					// NO ENCONTRADO O ERROR SERVIDOR
					return 0;
				}

			}
			// Si no se ha encontrado
			else {
				return 0;
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return 0;

	}
}
