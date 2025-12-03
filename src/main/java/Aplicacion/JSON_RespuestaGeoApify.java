package Aplicacion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSON_RespuestaGeoApify {
	
	@JsonProperty("features")
	private ArrayList <Lugares> coleccionFeatures;
	
	public JSON_RespuestaGeoApify() {
		
	}

	public ArrayList<Lugares> getColeccionFeatures() {
		return coleccionFeatures;
	}

	public void setColeccionFeatures(ArrayList<Lugares> coleccionFeatures) {
		this.coleccionFeatures = coleccionFeatures;
	}

	@Override
	public String toString() {
		return "JSON_RespuestaGeoApify [coleccionFeatures=" + coleccionFeatures + "]";
	}

	
	
	
	
}
