import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {

	public static void main(String[] args) {
		ObjectMapper objmap = new ObjectMapper();
		HttpClient client = HttpClient.newHttpClient(); // creación de cliente "por defecto"

		try {
			HttpRequest consulta = HttpRequest.newBuilder().uri(URI.create("https://restcountries.com/v3.1/name/spain")).build();
			HttpResponse<String> respuestaConsulta = client.send(consulta, BodyHandlers.ofString());
			List<Info_Pais> paises = objmap.readValue(respuestaConsulta.body(), new TypeReference<List<Info_Pais>>() {});
			
			for (Info_Pais paisActual : paises) {
				paisActual.fetchNeighborCountryNames();
				System.out.println(paisActual);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
