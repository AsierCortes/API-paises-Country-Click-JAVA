package Aplicacion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSON_RespuestaGeoApify {
	
	@JsonProperty("features")
	private List <Lugares> coleccionFeatures;
	
	public JSON_RespuestaGeoApify() {
		
	}

	public List<Lugares> getColeccionFeatures() {
		return coleccionFeatures;
	}

	public void setColeccionFeatures(List<Lugares> coleccionFeatures) {
		this.coleccionFeatures = coleccionFeatures;
	}
	
	
	
}
