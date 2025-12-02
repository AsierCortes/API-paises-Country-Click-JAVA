package Aplicacion;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info_Pais implements Serializable {
	@JsonProperty("name")
    private Nombre nombre;

    @JsonProperty("currencies")
    private Map<String, Moneda> moneda;
    private List<String> capital;
    private String region;

    @JsonProperty("languages")
    private HashMap<String, String> lenguajes;

    @JsonProperty("borders")
    private List<String> bordes;

    @JsonProperty("timezones")
    private List<String> zonaHoraria;

    @JsonProperty("population")
    private int poblacion;

//	private HttpClient client = HttpClient.newHttpClient();
//	private ObjectMapper objmapper = new ObjectMapper();
//	private ArrayList <String> bordersCountryName = new ArrayList<String>();

	public Info_Pais() {
	}
	
	public Nombre getNombre() {
		return nombre;
	}

	public void setNombre(Nombre nombre) {
		this.nombre = nombre;
	}

	public Map<String, Moneda> getMoneda() {
		return moneda;
	}

	public void setMoneda(Map<String, Moneda> moneda) {
		this.moneda = moneda;
	}

	public List<String> getCapital() {
		return capital;
	}

	public void setCapital(List<String> capital) {
		this.capital = capital;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public HashMap<String, String> getLenguajes() {
		return lenguajes;
	}

	public void setLenguajes(HashMap<String, String> lenguajes) {
		this.lenguajes = lenguajes;
	}

	public List<String> getBordes() {
		return bordes;
	}

	public void setBordes(List<String> bordes) {
		this.bordes = bordes;
	}

	public List<String> getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(List<String> zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	@Override
	public String toString() {
		return "Info_Pais [nombre=" + nombre.getNombreComun() + ", moneda=" + getNombreMoneda() + ", simbolo= "+ getSimboloMoneda() + ", capital=" + capital + ", region=" + region
				+ ", lenguajes=" + lenguajes + ", bordes=" + bordes + ", zonaHoraria=" + zonaHoraria + ", poblacion="
				+ poblacion + "]";
	}
	
	public String getNombreMoneda() {
		String claveUnica = moneda.keySet().iterator().next();
		String nombreMoneda = moneda.get(claveUnica).getNombre();
		return nombreMoneda;
	}
	public String getSimboloMoneda() {
		String claveUnica = moneda.keySet().iterator().next();
		String simboloMoneda = moneda.get(claveUnica).getSimbolo();
		return simboloMoneda;
	}
	
	
	
	// https://restcountries.com/v3.1/alpha/{code}?fields={field} -> code son
	// siempre 3 letras (ESP, AND, FRA etc.)
//	public void fetchNeighborCountryNames() {
//		try {
//			StringBuilder urlMoficada = new StringBuilder("https://restcountries.com/v3.1/alpha/cod?fields=name");
//
//			int pos = 37;
//
//			// Realizamos petición
//			for (int i = 0; i < borders.size(); i++) {
//				char[] letrasPaisCC3 = borders.get(i).toCharArray(); // Guardamos cada letra (AND -> A, N, D)
//
//				// Sustituimos
//				for (char c : letrasPaisCC3) {
//					urlMoficada.setCharAt(pos, c);
//					pos++;
//				}
//				String url = new String(urlMoficada);
//				HttpRequest consulta = HttpRequest.newBuilder().uri(URI.create(url)).build();
//				HttpResponse<String> respuesta = client.send(consulta, BodyHandlers.ofString());
//				// La url devuelve un JSON que se puede mapear
//				Map<String, Name> nameMap = objmapper.readValue(respuesta.body(), new TypeReference<Map<String, Name>>() {});
//				bordersCountryName.add(nameMap.get("name").getCommon());
//				pos = 37;
//				
//				
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<String> getBordersCountryName() {
//		return bordersCountryName;
//	}

	

	

}
