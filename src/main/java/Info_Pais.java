
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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Info_Pais implements Serializable{
	private Name name;
    private Map<String, Currency> currencies;
    private List<String> capital;
	private String region;
	private HashMap<String, String> languages;
	private List<String> borders;
    private List<String> timezones;
	private int population;
	
//	private HttpClient client = HttpClient.newHttpClient();
//	private ObjectMapper objmapper = new ObjectMapper();
//	private ArrayList <String> bordersCountryName = new ArrayList<String>();
	
	public Info_Pais() {
	}
	

	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Map<String, Currency> getCurrencies() {
		return currencies;
	}
	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
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
	public HashMap<String, String> getLanguages() {
		return languages;
	}
	public void setLanguages(HashMap<String, String> languages) {
		this.languages = languages;
	}
	public List<String> getBorders() {
		return borders;
	}
	public void setBorders(List<String> borders) {
		this.borders = borders;
	}
	public List<String> getTimezones() {
		return timezones;
	}
	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
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


	@Override
	public String toString() {
		return "Info_Pais [name=" + name.getCommon() + ", currencies=" + currencies.get("EUR").getName() + ". Simbolo=" + currencies.get("EUR").getSymbol() + ", capital=" + capital.get(0) + ", region=" + region
				+ ", languages=" + languages + ", borders=" + getBorders() + ", timezones=" + timezones + ", population="
				+ population + "]";
	}
	
}
