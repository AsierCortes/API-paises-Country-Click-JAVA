package Aplicacion;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionResultado implements Serializable {

	@JsonProperty("time_last_update_utc")
	private String fechaActualizacionPrecio;

	@JsonProperty("base_code")
	private String monedaOrigen;

	@JsonProperty("target_code")
	private String monedaResultado;

	@JsonProperty("conversion_rate")
	private double precioUnaUnidad;

	@JsonProperty("conversion_result")
	private double resultado;

	public ConversionResultado() {

	}

	public String getFechaActualizacionPrecio() {
		return fechaActualizacionPrecio;
	}

	public void setFechaActualizacionPrecio(String fechaActualizacionPrecio) {
		this.fechaActualizacionPrecio = fechaActualizacionPrecio;
	}

	public String getMonedaOrigen() {
		return monedaOrigen;
	}

	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}

	public String getMonedaResultado() {
		return monedaResultado;
	}

	public void setMonedaResultado(String monedaResultado) {
		this.monedaResultado = monedaResultado;
	}

	public double getPrecioUnaUnidad() {
		return precioUnaUnidad;
	}

	public void setPrecioUnaUnidad(double precioUnaUnidad) {
		this.precioUnaUnidad = precioUnaUnidad;
	}

	public double getResultado() {
		return resultado;
	}

	public void setResultado(double resultado) {
		this.resultado = resultado;
	}

	@Override
	public String toString() {
		return "ConversionResultado [fechaActualizacionPrecio=" + fechaActualizacionPrecio + ", monedaOrigen="
				+ monedaOrigen + ", monedaResultado=" + monedaResultado + ", precioUnaUnidad=" + precioUnaUnidad
				+ ", resultado=" + resultado + "]";
	}

}
