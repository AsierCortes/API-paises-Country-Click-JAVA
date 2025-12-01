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
4. Ver tiempo y pronósctico de temperaturas
       - Ver temperatura actual
       - Ver pronóstico de 1 a 7 días
       - Ver pronósticos pasado
       - Ver temperaturas máximas y mínimas de hoy
       - Ver clima
       
Clases:
- Buscador -> Es una clase nuestra, este permitirá realizar búsquedas de un pais (Todas las Apis)
       -> Métodos
       -> 1. Guardar objetos paises en un fichero (Serializar y deserializar objetos)
       -> 2. Guardar información pero escrita (FileWritter)
  
  
- Busqueda Viajes Aplication -> El usuario se comunicara con la aplicación por consola
- Info_Pais -> Aqui es donde se encuentra la información del pais, procediente de Rest Countries


Clase Buscador
Atributos: 
- Cliente
- ObjectMapper
- Lista paises Buscados       -> Por si quieres ver que paises a buscado anteriormente
- 


Geoapify:

apiKey → la que te dé Geoapify.
categories → qué quieres buscar (por ejemplo catering.restaurant).
filter o bias → para indicarle dónde buscar.
