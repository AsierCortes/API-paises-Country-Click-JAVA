APIS Utilizadas:
- Rest Countries  -> Información sobre paises
- ExchangeRate Api  -> Cambio de monedas
- open-meteo -> Ver tiempo en un pais concreto
- Geoapify -> Ver monumentos, restaurantes etc.

Idea principal: La aplicación va a ser como un asistente que te va ayudar a buscar un pais donde viajar.

Funcionalidades:
1. Introducir el pais al que se desea viajar
2. Informarte sobre datos de dicho pais.
       - Capital -> Para saber más o menos donde vas a aterrizar
       - Moneda
       - Idioma
       - Huso horario
       - Region
       - Población -> Un método que diga si es considerado una ciudad grande o pequeña dependiendo de la cantidad de habitantes
       - Paises alrededor, por si quiere visitar alguno cerca
3. Informarte sobre monumentos, restaurantes etc...
4. Hacer cambios de monedas  
5. Ver tiempo y pronósctico de temperaturas
       - Ver temperatura actual
       - Ver pronóstico de 1 a 7 días
       - Ver pronósticos pasado
       - Ver temperaturas máximas y mínimas de hoy
       - Ver clima
       
------------------------------------------------------------------------------------------------------------------


Fichero TXT:


Cosas pendientes: 
- Dar opción de que introduzca el pais a buscar información				-> HECHO
	-> opción 1: crear un hashmap con nombre en español y luego traducirlo a ingles -> HECHO
- Opción de añadir a favoritos								-> HECHO	
- Opción de serializar el objeto							-> HECHO
- Opción de guardarlo de un bloc de notas

Buscador, quiero intentar separar los métodos


---------------------------------------------------------------------------------------------

Métodos
Buscador:
BuscarInfoPais() -> Recibe el nombre del país en español, lo traduce a ingles y te devuelve la información
memoriaCaché -> nombre país (Ingles) | Info_Pais
añadirFavoritos() -> Añade un pais a una lista los países favoritos	(Pais_Info)
getPaisesFavoritos() -> Devuelve una la lista de países favoritos (Pais_Info)
getPaisesHistorial() -> Devuelve una lista de los países que han sido buscados (Pais_Info)
getConversionesRealizadasHistorial() -> devuelve una lista de las conversiones realizadas (ConversionResultado)
guardarInfoFicheroSerializar() -> Se recibe una ruta, se crea o se sobreescribe en ese fichero y se guarda la lista Info_Pais (Instancias) IMPORTANTE!! Falta guardar conversión, sitios cercanos y tiempo
deserializarInfoFichero() -> Falta lo mismo
conversionDinero() -> recibe dinero, monedaOrigen y monedaDestino, comprueba que son códigos de monedas correctas (a través del map) y realiza la llamada, si todo va correcto devuelve el resultado.


Falta:
verLugares()




PaisTraduccion
traducirPais() -> TRADUCE el nombre del pais de Español a Ingles. IMPORTANTE lo devuelve en lower case

CodigosValidosMonedas
devolverCodigoMoneda() -> Recibe el nombre de la moneda, y si esta existe devuelve el codigo de dicha moneda




---------------------------------------------------------------------------------------------
ApiExchange

Moneda y valor en otros precios 
-> https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/latest/ USD

Moneda y valor en un país en específico  (se basa en 1U -> 1 dólar a tal, 1 euro a tal) 
-> https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/pair/EUR/USD

Moneda y cambio en un país en concreto 
-> https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/pair/EUR/USD/3.24

Ver cuantas solicitudes nos queda
-> https://v6.exchangerate-api.com/v6/b14440c452c066c853a3b051/quota

----------------------------------------------------------------------------------------------

GeoApify

Buscar gimnasios en las rozas:
Ej -> https://api.geoapify.com/v2/places?categories=entertainment.cinema&bias.proximity:40.49084841629886,-3.8741767905296456&limit=20&apiKey=4efa5b9378404e32ba072c042705e870
Obligatorio: Clave API y una categoría

Usuario elije capital
Usuario elije distancia max km2

Buscar aeropuertos cercanos	-> airport
Buscar centros comerciales
	- Buscar centros comerciales de ropa -> commercial.clothing
	- Buscar tiendas de comida y alimentación -> commercial.food_and_drink
	- Buscar farmacias ->	commercial.health_and_beauty.pharmacy
	- Buscar mercadillos -> commercial.marketplace

Restaurantes
	- Restaurante Comida rápida -> catering.fast_food
	- Restaurante Kebab ->	catering.fast_food.kebab
	- Restaurante Pizzas -> catering.fast_food.pizza
	- Restaurantes tacos -> catering.fast_food.tacos
	- Restaurantes por lugares:
		- Restaurante argentino -> catering.restaurant.argentinian
		- Restaurante asiatico -> catering.restaurant.asian
		- Restaurante brasileño -> catering.restaurant.brazilian
		- Restaurante Japones -> catering.restaurant.japanese

Emergencias / Salud
	- Ambulancias -> emergency.ambulance_station
	- Hospitales -> healthcare.hospital
	- Entradas urgencias -> emergency.emergency_ward_entrance
	- Podólogos -> healthcare.podiatrist
	- Oculistas -> healthcare.optometrist
Entretenimiento
	- Cine -> entertainment.cinema
	- Cultura -> entertainment.culture
	- Teatros -> entertainment.culture.theatre
	- Museos -> entertainment.museum
	- Planetarios -> entertainment.planetarium
	- Zoo -> entertainment.zoo
	- Aquario -> entertainment.aquarium

	- Parques de atracciones:
		- Parque de atracciones -> entertainment.theme_park
		- Parque acuático ->	entertainment.water_park

	
	
------------------------------------------------------------------------------------------------
Presentación:
- De que iba el proyecto anterior
- Contar las tecnologías que usamos
- El programa que pasa si no hay wifi




Power point
1. Idea Principal y app anterior  ------> Bien lo unico foto chatbot
2. Funcionalidad --> EJEMPLO consola
3. Apis Usadas -> Nombras cada api. una foto de la propia foto del logo rest country.
	3.1 RestCountry
   	-> Foto de la esctructura json y
   	-> Clases para leer el json
   	-> Métodos más importantes -> Buscar infoPais()

   3.2 Exchange Rate api
   -> Foto de la esctructura json y
   -> Clases para leer el json
   -> Métodos mas importantes : conversionDInerio()

   3.3 GeoApify
   -> Foto de la esctructura json y
   -> Clases para leer el json
   -> Métodos imporatnesd: buscarlugarinfo()
   -> Información (filtrar, categorias, delimitadores, que funciona por coordanas)

4. Expiicar una llamada de cualquier api
5. Métodos extra
	5.1 SerializarFIchero
   	5.2 Deserailizar
	5.3 Ver paises favoritos
   	Fotos y explicar objetivo de cada método -> foto consola

6. Excepciones
   6.1 InputMismatch
   6.2 Si desconectas que no de error

7. DEMO
8. FIN
