# ✈️ Asistente de Viajes Virtual

¡Bienvenido al repositorio del **Asistente de Viajes**! 🌍

Este proyecto es una aplicación de consola desarrollada en Java que actúa como un asistente virtual e interactivo para la planificación de viajes. Su objetivo principal es centralizar y simplificar la investigación de tu próximo destino, evitando que tengas que buscar información dispersa en diferentes sitios web. Desde datos demográficos y geográficos, hasta herramientas financieras y un motor de búsqueda de puntos de interés locales, esta aplicación te ofrece todo lo necesario para organizar tu aventura desde la terminal.

---

## ✨ Funcionalidades Principales

Nuestra aplicación ofrece una experiencia fluida paso a paso con las siguientes capacidades:

* **Radiografía del Destino:** Introduce el nombre de un país y obtén un resumen instantáneo con datos vitales: capital (tu punto de aterrizaje), idioma, huso horario, región y una estimación de la actividad del lugar basada en su volumen de población.
* **Oficina Virtual de Cambio:** Una calculadora de divisas integrada. Introduce tu moneda local, la moneda de destino y tu presupuesto para obtener el valor convertido al instante y planificar tus gastos.
* **Explorador de la Capital:** Descubre puntos de interés en la capital del país. Busca en un directorio categorizado que incluye tiendas, transporte, servicios médicos de emergencia y lugares de entretenimiento (zoos, museos, teatros). Tú controlas el radio de búsqueda y la cantidad de resultados.
* **Gestión de Favoritos e Historial:** Guarda los países que más te gusten en una lista de favoritos y consulta el historial completo de tus búsquedas durante toda la sesión para comparar destinos.
* **Persistencia de la Planificación:** Al finalizar la sesión, puedes exportar y guardar todo tu historial, lugares buscados y conversiones a un archivo local mediante serialización de objetos, asegurando que tu itinerario quede guardado de forma segura.

---

## 🛠️ APIs Utilizadas

El núcleo de la aplicación se basa en la integración de tres servicios externos que nutren de datos reales al programa:

1.  **[REST Countries API](https://restcountries.com/):** Extrae información exhaustiva del país (coordenadas de la capital, moneda oficial, idiomas, población, etc.). Es la base que da contexto al resto de funcionalidades.
2.  **[ExchangeRate-API](https://www.exchangerate-api.com/):** Se encarga de procesar el cálculo y la conversión de divisas en base a tasas de cambio actualizadas, proporcionando una herramienta financiera precisa.
3.  **[Geoapify API](https://www.geoapify.com/):** Utilizada para el motor de búsqueda espacial. Localiza lugares específicos (hospitales, restaurantes, aeropuertos) mediante el uso de coordenadas de latitud y longitud dentro de un radio determinado.

---

## 💻 Tecnologías y Herramientas

* **Lenguaje:** Java
* **Peticiones HTTP:** `java.net.http.HttpClient` nativo de Java.
* **Manejo de JSON:** Librería `Jackson` (`ObjectMapper`, anotaciones `@JsonProperty` y `@JsonIgnoreProperties`) para mapear las respuestas de las APIs a objetos Java.
* **Estructuras de Datos:** Uso intensivo de `ArrayList`, `HashSet` y `HashMap` para gestionar la caché, historiales y mapeos de códigos.

---

## 📂 Estructura del Proyecto

A continuación se muestra la organización interna de los paquetes y clases del código fuente:

<br>
<p align="center">
  <img width="250" alt="estructura_carpetas" src="https://github.com/user-attachments/assets/30c3d036-51f1-4084-8af3-272c98928e9c" />
</p>
<br>

---

## 📸 Flujo de la Aplicación

A continuación, puedes ver un recorrido visual de cómo funciona el asistente en la terminal:

**1. Inicio de la Aplicación**
<br>
<p align="center">
  <img width="650" alt="Inicio de la Aplicación" src="https://github.com/user-attachments/assets/3cb66101-b8b0-44fa-a641-1ebc56eb2c00" />
</p>
<br>

**2. Búsqueda e Información del País**
<br>
<p align="center">
  <img width="650" alt="Información del País Buscado" src="https://github.com/user-attachments/assets/cfb54fc8-dd17-4d4e-ab95-ba4cc2b215fb" />
</p>
<br>

**3. Cambio de Divisas**
<br>
<p align="center">
  <img width="650" alt="Cambio de Divisas" src="https://github.com/user-attachments/assets/f4702977-6cb3-45e4-ab84-71a1683a38ae" />
</p>
<br>

**4. Búsqueda de Lugares en la Capital**
<br>
<p align="center">
  <img width="650" alt="Menú de Categorías de Búsqueda" src="https://github.com/user-attachments/assets/4d56eca1-de44-4249-9e07-1313dc64be9a" />
</p>

<br>
<p align="center">
  <img width="500" alt="Resumen de los Parámetros de Búsqueda" src="https://github.com/user-attachments/assets/e36d9f98-c5c6-49de-ae62-6b9492db2608" />
</p>

<br>
<p align="center">
  <img width="650" alt="Resultados de los Lugares" src="https://github.com/user-attachments/assets/08f4a1c6-7526-4bd7-8426-5002bdc46f70" />
</p>
<br>

**5. Fin de la Sesión y Guardado**
<br>
<p align="center">
  <img width="500" alt="Fin de la Sesión y Despedida" src="https://github.com/user-attachments/assets/1abe3ffd-8416-4b5b-a4cd-d245cdd5a729" />
</p>
<br>

---

## ⚠️ Nota Importante sobre Configuración

> **Advertencia de Seguridad:** Este repositorio requiere el uso de claves API (API Keys) válidas para **ExchangeRate-API** y **Geoapify API**. Por motivos de seguridad, las claves privadas no deben subirse al repositorio público.
> 
> Para ejecutar este proyecto localmente, asegúrate de registrarte en los servicios mencionados, obtener tus propias API Keys y configurarlas correctamente en la clase `Buscador.java`.
