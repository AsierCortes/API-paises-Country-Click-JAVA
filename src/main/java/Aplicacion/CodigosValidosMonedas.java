package Aplicacion;

import java.util.HashMap;
import java.util.Map;

public class CodigosValidosMonedas {
	private Map <String, String> codigosMonedas;
	
	public CodigosValidosMonedas() {
		this.codigosMonedas = new HashMap<String, String>();
		codigosMonedas.put("peso argentino", "ARS");
		codigosMonedas.put("boliviano", "BOB");
		codigosMonedas.put("real brasileno", "BRL");
		codigosMonedas.put("peso chileno", "CLP");
		codigosMonedas.put("peso colombiano", "COP");
		codigosMonedas.put("colon costarricense", "CRC");
		codigosMonedas.put("peso cubano", "CUP");
		codigosMonedas.put("peso dominicano", "DOP");
		codigosMonedas.put("euro", "EUR");
		codigosMonedas.put("quetzal guatemalteco", "GTQ");
		codigosMonedas.put("lempira hondureno", "HNL");
		codigosMonedas.put("peso mexicano", "MXN");
		codigosMonedas.put("cordoba nicaraguense", "NIO");
		codigosMonedas.put("balboa panameno", "PAB");
		codigosMonedas.put("sol peruano", "PEN");
		codigosMonedas.put("guarani paraguayo", "PYG");
		codigosMonedas.put("dolar", "USD");
		codigosMonedas.put("peso uruguayo", "UYU");
		codigosMonedas.put("bolivar venezolano", "VES");
		codigosMonedas.put("dolar australiano", "AUD");
		codigosMonedas.put("dolar canadiense", "CAD");
		codigosMonedas.put("franco suizo", "CHF");
		codigosMonedas.put("yuan chino", "CNY");
		codigosMonedas.put("libra esterlina", "GBP");
		codigosMonedas.put("yen japones", "JPY");
		codigosMonedas.put("rublo ruso", "RUB");
		codigosMonedas.put("rupia india", "INR");
		codigosMonedas.put("dirham de los eau", "AED");
		codigosMonedas.put("afgani afgano", "AFN");
		codigosMonedas.put("florin antillano", "ANG");
		codigosMonedas.put("dolar bahameno", "BSD");
		codigosMonedas.put("dolar beliceno", "BZD");
		codigosMonedas.put("corona danesa", "DKK");
		codigosMonedas.put("libra egipcia", "EGP");
		codigosMonedas.put("dolar de hong kong", "HKD");
		codigosMonedas.put("rupia indonesia", "IDR");
		codigosMonedas.put("nuevo sequel israeli", "ILS");
		codigosMonedas.put("won surcoreano", "KRW");
		codigosMonedas.put("dirham marroqui", "MAD");
		codigosMonedas.put("corona noruega", "NOK");
		codigosMonedas.put("dolar neozelandes", "NZD");
		codigosMonedas.put("zloty polaco", "PLN");
		codigosMonedas.put("riyal qatari", "QAR");
		codigosMonedas.put("riyal saudi", "SAR");
		codigosMonedas.put("corona sueca", "SEK");
		codigosMonedas.put("dolar de singapur", "SGD");
		codigosMonedas.put("baht tailandes", "THB");
		codigosMonedas.put("lira turca", "TRY");
		codigosMonedas.put("grivna ucraniana", "UAH");
		codigosMonedas.put("rand sudafricano", "ZAR");
	}
	// Recibe el nombre de la moneda, y si esta existe devuelve el codigo de dicha moneda
	public String devolverCodigoMoneda(String nombreMoneda) {
		String moneda = nombreMoneda.toLowerCase();
		
		// comprueba que este en el map
		if(codigosMonedas.containsKey(moneda)) {
			return codigosMonedas.get(moneda);	// lo devuelve
		}
		// si no esta devuelve null
		else {
			return null;
		}
	}
	
	
}
