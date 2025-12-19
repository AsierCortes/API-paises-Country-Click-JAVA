package Aplicacion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LugarBuscadoInfo {
	@JsonProperty("name")
	private String nombre;

	@JsonProperty("formatted")
	private String ubicacion;

	@JsonProperty("opening_hours")
	private String horario;

	public LugarBuscadoInfo() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
	@Override
	public String toString() {
		return "LugarBuscadoInfo [nombre=" + nombre + ", ubicacion=" + ubicacion + ", horario=" + horario + "]";
	}
}
