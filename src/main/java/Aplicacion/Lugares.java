package Aplicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lugares {
	
	@JsonProperty("properties")
	private LugarBuscadoInfo informacion;
	
	public Lugares() {
		
	}
	
	public LugarBuscadoInfo getInformacion() {
		return informacion;
	}

	public void setInformacion(LugarBuscadoInfo informacion) {
		this.informacion = informacion;
	}

}
